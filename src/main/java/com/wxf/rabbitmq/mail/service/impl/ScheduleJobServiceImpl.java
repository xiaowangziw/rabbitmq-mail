package com.wxf.rabbitmq.mail.service.impl;

import com.wxf.rabbitmq.mail.mapper.ScheduleJobMapper;
import com.wxf.rabbitmq.mail.pojo.ScheduleJob;
import com.wxf.rabbitmq.mail.service.ScheduleJobService;
import com.wxf.rabbitmq.mail.task.ScheduleUtils;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.quartz.Scheduler;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务
 */
@Service("scheduleJobService")
@Transactional(rollbackFor = Exception.class)
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired(required = false)
    private ScheduleJobMapper scheduleJobMapper;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.selectAll();

        for (ScheduleJob scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }



    @Override
    public void update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
    }

    @Override
    public int updateBatch(List<String> jobIds, String status) {
        return scheduleJobMapper.updateBatch(jobIds, status);
    }


}


