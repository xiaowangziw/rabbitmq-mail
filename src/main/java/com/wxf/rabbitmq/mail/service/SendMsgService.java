package com.wxf.rabbitmq.mail.service;

import com.wxf.rabbitmq.mail.constant.ServerResponse;
import com.wxf.rabbitmq.mail.pojo.Mail;


public interface SendMsgService {

    ServerResponse testIdempotence();

    ServerResponse send(Mail mail);

    ServerResponse accessLimit();

}
