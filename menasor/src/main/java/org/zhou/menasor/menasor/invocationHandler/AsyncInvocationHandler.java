package org.zhou.menasor.menasor.invocationHandler;

import org.zhou.menasor.async.producer.service.ProducerService;
import org.zhou.menasor.async.producer.service.impl.KafkaInfoServiceImpl;
import org.zhou.menasor.async.serialization.dto.AsyncDTO;
import org.zhou.menasor.menasor.annotation.AsyncKey;
import org.zhou.menasor.menasor.util.CommonUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by DT283 on 2017/7/3.
 */

public class AsyncInvocationHandler implements InvocationHandler {
    private ProducerService producer;

    private Class<?> classType;

    public AsyncInvocationHandler(Class<?> classType, Properties properties) {
        this.classType = classType;
        producer = new KafkaInfoServiceImpl(properties);
    }

    public Class<?> getClassType() {
        return classType;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String className = method.getDeclaringClass().getName();
        if (!method.getName().equals("toString")) {
            AsyncDTO asyncDTO = new AsyncDTO();
            asyncDTO.setMethod(method.getName());
            asyncDTO.setParameterTypes(method.getParameterTypes());
            asyncDTO.setParameters(CommonUtil.objectArrayToJsonStringArray(args));
            String key = null;
            AsyncKey asyncKey = method.getAnnotation(AsyncKey.class);
            if (asyncKey != null && asyncKey.keySeq() != -1) {
                key = args[asyncKey.keySeq()].toString();
            }
            producer.sendMessage(className, key, asyncDTO);
        }
        return className;
    }
}
