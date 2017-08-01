package org.zhou.menasor.menasor.demo.service.impl;

import org.springframework.stereotype.Service;
import org.zhou.menasor.menasor.demo.dto.User;
import org.zhou.menasor.menasor.demo.service.Demo2Service;

/**
 * Created by DT283 on 2017/7/3.
 */
@Service
public class Demo2ServiceImpl implements Demo2Service {
    @Override
    public void send(int i) {
        System.out.println("Demo2ServiceImpl:" + i);
    }

    @Override
    public void send(String message) {
        System.out.println("Demo2ServiceImpl:" + message);
    }

    @Override
    public void send(User user) {
        System.out.println("Demo2ServiceImpl:" + user);
    }
}
