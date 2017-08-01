package org.zhou.menasor.menasor.demo.service.impl;

import org.springframework.stereotype.Service;
import org.zhou.menasor.menasor.demo.dto.User;
import org.zhou.menasor.menasor.demo.service.Demo2Service;
import org.zhou.menasor.menasor.demo.service.DemoService;

import javax.annotation.Resource;

/**
 * Created by DT283 on 2017/7/3.
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Resource
    private Demo2Service demo2Service;

    @Override
    public void send(int i) {
        System.out.println("DemoServiceImpl:" + i);
        demo2Service.send(i);
    }

    @Override
    public void send(String message) {
        System.out.println("DemoServiceImpl:" + message);
        demo2Service.send(message);
    }

    @Override
    public void send(User user) {
        System.out.println("DemoServiceImpl:" + user);
        demo2Service.send(user);
    }

    @Override
    public void send(User user, int i, String message) {
        if (i % 1000000 == 999999) {
            System.out.println("DemoServiceImpl:" + user + "," + i + "," + message);
            System.out.println(System.currentTimeMillis());
        }
    }
}
