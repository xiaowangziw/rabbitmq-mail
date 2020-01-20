package com.wxf.rabbitmq.mail.utils;

import java.util.UUID;

public class CommonUtils {

    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        id = id.replaceAll("-", "");
        return id;
    }
}
