package com.wxf.rabbitmq.mail.service.impl;

import com.wxf.rabbitmq.mail.config.RabbitConfig;
import com.wxf.rabbitmq.mail.constant.ResponseCode;
import com.wxf.rabbitmq.mail.constant.ServerResponse;
import com.wxf.rabbitmq.mail.consumer.MessageHelper;
import com.wxf.rabbitmq.mail.mapper.MsgLogMapper;
import com.wxf.rabbitmq.mail.pojo.Mail;
import com.wxf.rabbitmq.mail.pojo.MsgLog;
import com.wxf.rabbitmq.mail.service.SendMsgService;
import com.wxf.rabbitmq.mail.utils.RandomUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMsgServiceImpl implements SendMsgService {

    @Autowired(required = false)
    private MsgLogMapper msgLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public ServerResponse send(Mail mail) {
        String msgId = RandomUtil.UUID32();
        mail.setMsgId(msgId);

        MsgLog msgLog = new MsgLog(msgId, mail, RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME);
        // 消息入库
        msgLogMapper.insert(msgLog);

        CorrelationData correlationData = new CorrelationData(msgId);
        // 发送消息
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);

        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

    @Override
    public ServerResponse accessLimit() {
        return ServerResponse.success("accessLimit: success");
    }

}
