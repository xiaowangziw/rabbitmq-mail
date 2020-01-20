package com.wxf.rabbitmq.mail.mapper;


import com.wxf.rabbitmq.mail.pojo.ScheduleJob;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ScheduleJobMapper extends Mapper<ScheduleJob> {

    int updateBatch(List<String> jobIds, String status);
}
