package org.zhou.menasor.menasor.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.TimeZone;

/**
 * Created by DT283 on 2016/7/7.
 */
public class CommonUtil {

    private static ObjectMapper objectMapper = getObjectMapper();

    public static Class<?>[] getParameterClasses(String[] parameterTypes) throws ClassNotFoundException {
        Class<?>[] parameterClasses = new Class<?>[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            if ("long".equals(parameterTypes[i])) {
                parameterClasses[i] = long.class;
            } else if ("int".equals(parameterTypes[i])) {
                parameterClasses[i] = int.class;
            } else if ("short".equals(parameterTypes[i])) {
                parameterClasses[i] = short.class;
            } else if ("short".equals(parameterTypes[i])) {
                parameterClasses[i] = short.class;
            } else if ("float".equals(parameterTypes[i])) {
                parameterClasses[i] = float.class;
            } else if ("char".equals(parameterTypes[i])) {
                parameterClasses[i] = char.class;
            } else if ("byte".equals(parameterTypes[i])) {
                parameterClasses[i] = byte.class;
            } else if ("boolean".equals(parameterTypes[i])) {
                parameterClasses[i] = boolean.class;
            } else {
                parameterClasses[i] = Class.forName(parameterTypes[i]);
            }
        }
        return parameterClasses;
    }

    public static String[] classArrayToStringArray(Class[] classArray) throws JsonProcessingException {
        String[] jsonArray = new String[classArray.length];
        for (int i = 0; i < classArray.length; i++) {
            jsonArray[i] = classArray[i].getName();
        }
        return jsonArray;
    }

    public static String[] objectArrayToJsonStringArray(Object[] objectArray) throws JsonProcessingException {
        String[] jsonArray = new String[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            jsonArray[i] = getObjectMapper().writeValueAsString(objectArray[i]);
        }
        return jsonArray;
    }

    public static Object[] jsonStringArrayToObjectArray(String[] jsonArray, Class[] classes) throws IOException {
        Object[] objectArray = new Object[jsonArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            objectArray[i] = getObjectMapper().readValue(jsonArray[i], classes[i]);
        }
        return objectArray;
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = buildDefaultObjectMapper();
        }
        return objectMapper;
    }

    public static ObjectMapper buildDefaultObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setTimeZone(TimeZone.getDefault());
        return objectMapper;
    }
}
