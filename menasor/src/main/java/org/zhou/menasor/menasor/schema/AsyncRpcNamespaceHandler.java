package org.zhou.menasor.menasor.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by DT283 on 2017/7/4.
 */
public class AsyncRpcNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("consumer", new AsyncRpcBeanDefinitionParser());
        registerBeanDefinitionParser("producer", new AsyncRpcBeanDefinitionParser());
    }
}
