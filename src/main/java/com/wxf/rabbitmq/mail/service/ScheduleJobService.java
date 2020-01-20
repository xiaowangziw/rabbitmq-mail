package com.wxf.rabbitmq.mail.service;

import com.wxf.rabbitmq.mail.pojo.ScheduleJob;

import java.util.List;

/**
 * 定时任务
 * author : zhangyongzhao
 * createTime : 2019/5/29
 */
public interface ScheduleJobService {


    /**
     * 更新定时任务
     */
    void update(ScheduleJob scheduleJob);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(List<String> jobIds, String status);

}


