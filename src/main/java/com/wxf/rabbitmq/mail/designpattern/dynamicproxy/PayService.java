package com.wxf.rabbitmq.mail.designpattern.dynamicproxy;

import java.math.BigDecimal;

public interface PayService {

    void pay(String username, BigDecimal money);

    void a();

    void b();

}
