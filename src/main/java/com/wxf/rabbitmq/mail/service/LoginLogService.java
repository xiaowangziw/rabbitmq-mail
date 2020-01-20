package com.wxf.rabbitmq.mail.service;


import com.wxf.rabbitmq.mail.pojo.LoginLog;

public interface LoginLogService {

    void insert(LoginLog loginLog);

    LoginLog selectByMsgId(String msgId);

}
