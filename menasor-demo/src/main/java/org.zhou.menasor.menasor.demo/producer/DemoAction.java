package org.zhou.menasor.menasor.demo.producer;

import org.springframework.stereotype.Service;
import org.zhou.menasor.menasor.demo.dto.User;
import org.zhou.menasor.menasor.demo.service.DemoService;

import javax.annotation.Resource;
import java.util.Random;


/**
 * Created by DT283 on 2017/7/5.
 */
@Service
public class DemoAction {
    @Resource
    private DemoService demoService;

    public void doAction() {
        User user = new User();
        user.setName("zhoudan");
        user.setPasswd(String.valueOf(new Random().nextLong()));
        for (int i = 0; i < 10000000; i++) {
            /*demoService.send(user);
            demoService.send(i);
            demoService.send("abc");*/
            demoService.send(user, i, "abc");
        }
    }
}
