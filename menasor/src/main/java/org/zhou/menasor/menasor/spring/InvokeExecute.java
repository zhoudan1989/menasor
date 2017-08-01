package org.zhou.menasor.menasor.spring;

import org.springframework.util.ReflectionUtils;
import org.zhou.menasor.menasor.util.CommonUtil;

import java.lang.reflect.Method;

/**
 * Created by DT283 on 2017/7/3.
 */
public class InvokeExecute {
    public static boolean execute(Object clazz, String methodName, String[] parameters, String[] parameterTypes) throws Exception {
        Class[] parameterClasses = CommonUtil.getParameterClasses(parameterTypes);
        return execute(clazz, methodName, parameters, parameterClasses);
    }

    public static boolean execute(Object clazz, String methodName, String[] parameters, Class[] parameterClasses) throws Exception {
        Method method = ReflectionUtils.findMethod(clazz.getClass(), methodName, parameterClasses);
        Object[] parameter = CommonUtil.jsonStringArrayToObjectArray(parameters, parameterClasses);
        ReflectionUtils.invokeMethod(method, clazz, parameter);
        return true;
    }
}
