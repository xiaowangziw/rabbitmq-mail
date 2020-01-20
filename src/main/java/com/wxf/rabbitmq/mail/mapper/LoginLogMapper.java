package com.wxf.rabbitmq.mail.mapper;


import com.wxf.rabbitmq.mail.pojo.LoginLog;

public interface LoginLogMapper {

    void insert(LoginLog loginLog);

    LoginLog selectByMsgId(String msgId);

}
