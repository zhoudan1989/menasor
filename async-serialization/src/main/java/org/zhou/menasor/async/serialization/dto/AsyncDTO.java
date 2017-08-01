package org.zhou.menasor.async.serialization.dto;

/**
 * Created by DT283 on 2017/7/12.
 */
public class AsyncDTO {
    private String method;

    private String[] parameters;

    private Class[] parameterTypes;

    public String getMethod() {
        return method;
    }

    public AsyncDTO setMethod(String method) {
        this.method = method;
        return this;
    }

    public String[] getParameters() {
        return parameters;
    }

    public AsyncDTO setParameters(String[] parameters) {
        this.parameters = parameters;
        return this;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public AsyncDTO setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }
}
