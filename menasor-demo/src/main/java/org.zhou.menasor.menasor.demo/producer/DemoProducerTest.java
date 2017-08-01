package org.zhou.menasor.menasor.demo.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by DT283 on 2017/7/3.
 */
public class DemoProducerTest {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"producer.xml"});
        context.start();
        try {
            Thread.sleep(2000);
            DemoAction demoAction = (DemoAction) SpringContextUtil.getBean("demoAction");
            demoAction.doAction();

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
