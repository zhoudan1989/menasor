package org.zhou.menasor.menasor.demo.demo.demo3;

/**
 * Created by DT283 on 2017/7/5.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PojoInvocationHandler implements InvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("Hello!");

        return null;
    }
}
