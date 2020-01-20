package com.wxf.rabbitmq.mail.controller;

import com.wxf.rabbitmq.mail.annotation.AccessLimit;
import com.wxf.rabbitmq.mail.annotation.ApiIdempotent;
import com.wxf.rabbitmq.mail.constant.ServerResponse;
import com.wxf.rabbitmq.mail.service.SendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private SendMsgService sendMsgService;

    @ApiIdempotent
    @PostMapping("testIdempotence")
    public ServerResponse testIdempotence() {
        return sendMsgService.testIdempotence();
    }

    @AccessLimit(maxCount = 5, seconds = 5)
    @PostMapping("accessLimit")
    public ServerResponse accessLimit() {
        return sendMsgService.accessLimit();
    }


}
