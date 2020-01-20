package com.wxf.rabbitmq.mail.service.impl;

import com.wxf.rabbitmq.mail.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 日志查询
 */
@Service("scheduleJobLogService")
@Transactional(rollbackFor = Exception.class)
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
}


