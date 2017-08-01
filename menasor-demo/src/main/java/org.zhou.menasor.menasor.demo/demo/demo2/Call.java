package org.zhou.menasor.menasor.demo.demo.demo2;

import java.io.Serializable;

/**
 * Created by DT283 on 2017/7/3.
 */
public class Call implements Serializable {
    private static final long serialVersionUID = 5386052199960133937L;
    private String className; // 调用的类名或接口名
    private String methodName; // 调用的方法名
    private Class<?>[] paramTypes; // 方法参数类型
    private Object[] params; // 调用方法时传入的参数值
    /**
     * 表示方法的执行结果 如果方法正常执行,则 result 为方法返回值,
     * 如果方法抛出异常,那么 result 为该异常。
     */
    private Object result;

    public Call() {
    }

    public Call(String className, String methodName, Class<?>[] paramTypes, Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String getClassName() {
        return className;
    }

    public Call setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public Call setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public Call setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
        return this;
    }

    public Object[] getParams() {
        return params;
    }

    public Call setParams(Object[] params) {
        this.params = params;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public Call setResult(Object result) {
        this.result = result;
        return this;
    }
}
