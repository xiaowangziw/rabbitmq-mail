package com.wxf.rabbitmq.mail.listener;

import com.rabbitmq.client.Channel;
import com.wxf.rabbitmq.mail.config.RabbitConfig;
import com.wxf.rabbitmq.mail.consumer.BaseConsumer;
import com.wxf.rabbitmq.mail.consumer.BaseConsumerProxy;
import com.wxf.rabbitmq.mail.consumer.MailConsumer;
import com.wxf.rabbitmq.mail.service.MsgLogService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MailListener {

    @Autowired
    private MailConsumer mailConsumer;

    @Autowired
    private MsgLogService msgLogService;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(mailConsumer, msgLogService);
        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
        if (null != proxy) {
            proxy.consume(message, channel);
        }
    }

}
