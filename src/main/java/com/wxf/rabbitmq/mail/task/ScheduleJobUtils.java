package com.wxf.rabbitmq.mail.task;

import com.wxf.rabbitmq.mail.constant.ScheduleConstant;
import com.wxf.rabbitmq.mail.mapper.ScheduleJobLogMapper;
import com.wxf.rabbitmq.mail.pojo.ScheduleJob;
import com.wxf.rabbitmq.mail.pojo.ScheduleJobLog;
import com.wxf.rabbitmq.mail.utils.CommonUtils;
import com.wxf.rabbitmq.mail.utils.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 定时任务工具
 */
@Component
@Slf4j
public class ScheduleJobUtils extends QuartzJobBean {

    private ExecutorService service = Executors.newSingleThreadExecutor();



    @Autowired(required = false)
    private ScheduleJobLogMapper scheduleJobLogMapper;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap()
                .get(ScheduleConstant.JOB_PARAM_KEY);
        ScheduleJobLog jobLog = new ScheduleJobLog();
        String lockKey = "job_" + "mail" + "_" + scheduleJob.getJobId();
        int time = 60*1000;
        if (scheduleJob.getInvalidTime() != null && scheduleJob.getInvalidTime() != 0) {
            time = scheduleJob.getInvalidTime();
        }
        boolean isLock = jedisUtil.tryGetDistributedLock(lockKey, lockKey, time);
        if (!isLock) {
            return;
        }

        //数据库保存执行记录
        jobLog.setLogId(CommonUtils.generateId());
        jobLog.setJobId(scheduleJob.getJobId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setMethodName(scheduleJob.getMethodName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setIpAddress(getHostIP());
        jobLog.setCreateTime(new Date());
        //任务开始时间
        long startTime = System.currentTimeMillis();
        try {
            //执行任务
            log.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
                    scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);

            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes((int) times);
            //任务状态    1：成功    0：失败
            jobLog.setStatus(ScheduleConstant.PAUSE);

            log.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            log.info("任务执行失败，任务ID：" + scheduleJob.getJobId());

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes((int) times);

            //任务状态    0：成功    1：失败
            jobLog.setStatus(ScheduleConstant.NORMAL);
            jobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            if (jobLog != null) {
                scheduleJobLogMapper.insert(jobLog);
            }
            jedisUtil.releaseDistributedLock( lockKey, lockKey);
        }
    }

    //获取本地IP地址
    private String getHostIP() {
        String tempIP = "127.0.0.1";
        try {
            tempIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e1) {
            return tempIP;
        }
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements()) {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements()) {
                    ip = addrs.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(tempIP)) {
                        return ip.getHostAddress();
                    }
                }
            }
            return tempIP;
        } catch (Exception e) {
            return tempIP;
        }
    }

}



