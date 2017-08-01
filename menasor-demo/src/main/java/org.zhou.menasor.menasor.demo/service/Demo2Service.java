package org.zhou.menasor.menasor.demo.service;


import org.zhou.menasor.menasor.demo.dto.User;

/**
 * Created by DT283 on 2017/7/3.
 */
public interface Demo2Service {
    void send(int i);

    void send(String message);

    void send(User user);
}
