package com.wxf.rabbitmq.mail.service.impl;

import com.wxf.rabbitmq.mail.mapper.LoginLogMapper;
import com.wxf.rabbitmq.mail.pojo.LoginLog;
import com.wxf.rabbitmq.mail.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired(required = false)
    private LoginLogMapper loginLogMapper;

    @Override
    public void insert(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }

    @Override
    public LoginLog selectByMsgId(String msgId) {
        return loginLogMapper.selectByMsgId(msgId);
    }

}
