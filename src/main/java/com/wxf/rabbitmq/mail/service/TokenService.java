package com.wxf.rabbitmq.mail.service;

import com.wxf.rabbitmq.mail.constant.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    ServerResponse createToken();

    void checkToken(HttpServletRequest request);

}
