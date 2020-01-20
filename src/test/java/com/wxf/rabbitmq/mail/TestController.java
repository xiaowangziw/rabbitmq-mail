package com.wxf.rabbitmq.mail;

import com.wxf.rabbitmq.mail.utils.JedisUtil;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {

    @Autowired
    JedisUtil jedisUtil;

    @Test
    public void test(){
        boolean b = jedisUtil.tryGetDistributedLock("mail", "mail", 60 * 1000);
        System.out.println(b);

        boolean b1 = jedisUtil.releaseDistributedLock("mail", "mail");
        System.out.println(b1);
    }
}
