package org.zhou.menasor.menasor.demo.demo.demo1;

import java.lang.reflect.Proxy;

/**
 * Created by DT283 on 2017/7/3.
 */
public class Invoker {
    public Object getInstance(Class<?> cls) {
        KafkaProxy invocationHandler = new KafkaProxy();
        Object newProxyInstance = Proxy.newProxyInstance(
                cls.getClassLoader(),
                new Class[]{cls},
                invocationHandler);
        return (Object) newProxyInstance;
    }
}
