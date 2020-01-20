package com.wxf.rabbitmq.mail.service;

import com.wxf.rabbitmq.mail.constant.ServerResponse;
import com.wxf.rabbitmq.mail.pojo.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getOne(Integer id);

    void add(User user);

    void update(User user);

    void delete(Integer id);

    User getByUsernameAndPassword(String username, String password);

    ServerResponse login(String username, String password);

}
