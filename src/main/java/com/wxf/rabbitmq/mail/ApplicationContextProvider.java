package com.wxf.rabbitmq.mail;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class ApplicationContextProvider {

    private static ApplicationContext context;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass) {
        return context.getBean(tClass);
    }

    /**
     * 根据名称获取Bean
     *
     * @param name
     * @return
     */
    public static Object getBeanByName(String name) {
        return context.getBean(name);
    }
}
