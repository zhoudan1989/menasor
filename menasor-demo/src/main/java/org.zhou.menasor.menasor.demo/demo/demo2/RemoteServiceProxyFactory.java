package org.zhou.menasor.menasor.demo.demo.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by DT283 on 2017/7/3.
 */
public class RemoteServiceProxyFactory {

    public static Object getRemoteServiceProxy(InvocationHandler h) {
        Class<?> classType = ((ServiceInvocationHandler) h).getClassType();
        // 获取动态代理类
        Object proxy = Proxy.newProxyInstance(classType.getClassLoader(),
                new Class[]{classType}, h);
        return proxy;
    }
}
