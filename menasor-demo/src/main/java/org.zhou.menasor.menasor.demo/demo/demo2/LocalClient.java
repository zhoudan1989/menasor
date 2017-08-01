package org.zhou.menasor.menasor.demo.demo.demo2;

import java.lang.reflect.InvocationHandler;

/**
 * Created by DT283 on 2017/7/3.
 */
public class LocalClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        Integer port = 8001;
        Class<?> classType = Service.class;
        InvocationHandler h = new ServiceInvocationHandler(classType, host, port);
        Service serviceProxy = (Service) RemoteServiceProxyFactory.getRemoteServiceProxy(h);
        String result = serviceProxy.getService("SunnyMarkLiu", 22);
        System.out.println("调用远程方法getService的结果：" + result);
    }
}
