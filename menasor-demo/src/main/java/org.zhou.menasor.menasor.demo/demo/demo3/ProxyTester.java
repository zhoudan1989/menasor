package org.zhou.menasor.menasor.demo.demo.demo3;

/**
 * Created by DT283 on 2017/7/5.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTester {

    public static void main(String[] args) {
        ApplicationContext contex = new ClassPathXmlApplicationContext("spring-demo3.xml");
        AnyInterface tester = (AnyInterface) contex.getBean("anyInterface");
        tester.sayHello();

        /* Implemented with the previous code */
//      callProxy();
    }

    /**
     * @deprecated explanation of why function was deprecated, if possible include what
     * should be used.
     */
    @Deprecated
    public static void callProxy() {
        InvocationHandler handler = new PojoInvocationHandler();
        AnyInterface proxy = (AnyInterface) Proxy.newProxyInstance(
                AnyInterface.class.getClassLoader(),
                new Class[]{AnyInterface.class},
                handler);
        proxy.sayHello();
    }

}