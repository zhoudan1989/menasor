package org.zhou.menasor.menasor.demo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by DT283 on 2017/7/3.
 */
public class DemoConsumerTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        while (true) {
            Thread.sleep(10 * 60 * 1000);
        }
    }
}
