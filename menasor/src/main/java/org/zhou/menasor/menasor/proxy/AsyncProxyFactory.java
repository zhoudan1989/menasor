package org.zhou.menasor.menasor.proxy;


import org.zhou.menasor.menasor.invocationHandler.AsyncInvocationHandler;
import org.zhou.menasor.menasor.config.ProducerConfig;

import java.lang.reflect.Proxy;

/**
 * Created by DT283 on 2017/7/3.
 */
public class AsyncProxyFactory {

    public static Object getKafkaProxy(ProducerConfig config) throws ClassNotFoundException {
        Class<?> classType = Class.forName(config.getInterfac());
        AsyncInvocationHandler h = new AsyncInvocationHandler(classType, config.getProperties());
        // 获取动态代理类
        Object proxy = Proxy.newProxyInstance(classType.getClassLoader(),
                new Class[]{classType}, h);
        return proxy;
    }
}
