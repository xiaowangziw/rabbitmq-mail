package com.wxf.rabbitmq.mail.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Integer id;
    private String username;
    private String password;

}
