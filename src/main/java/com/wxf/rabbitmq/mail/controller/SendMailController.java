package com.wxf.rabbitmq.mail.controller;

import com.wxf.rabbitmq.mail.constant.ResponseCode;
import com.wxf.rabbitmq.mail.constant.ServerResponse;
import com.wxf.rabbitmq.mail.pojo.Mail;
import com.wxf.rabbitmq.mail.service.SendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailSend")
@Slf4j
public class SendMailController {

    @Autowired
    private SendMsgService sendMsgService;


    @PostMapping("single")
    public ServerResponse sendMail(@Validated Mail mail, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ServerResponse.error(msg);
        }

        return sendMsgService.send(mail);
    }

    @PostMapping("batch")
    public ServerResponse batchSend(@RequestParam String content,@RequestParam String title,@RequestParam String tos) {
        String[] split = tos.split(",");
        for (int i =0 ;i < split.length; i++){
            Mail mail = new Mail();
            mail.setContent(content);
            mail.setTitle(title);
            mail.setTo(split[i]);
            sendMsgService.send(mail);
        }

        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }

}
