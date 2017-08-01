package org.zhou.menasor.menasor.schema;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;
import org.zhou.menasor.menasor.invocationHandler.AsyncInvocationHandler;
import org.zhou.menasor.menasor.properties.KafkaProperties;
import org.zhou.menasor.menasor.proxy.AsyncProxyExecute;
import org.zhou.menasor.menasor.proxy.AsyncProxyFactory;

import java.util.Properties;

/**
 * Created by DT283 on 2017/7/4.
 */
public class AsyncRpcBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    private final static Logger logger = LogManager.getLogger(AsyncRpcBeanDefinitionParser.class);

    @Override
    protected Class<?> getBeanClass(Element element) {
        if (element.getLocalName().equals("consumer")) {
            return AsyncProxyExecute.class;
        } else if (element.getLocalName().equals("producer")) {
            return AsyncProxyFactory.class;
        }
        return null;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        try {
            if (element.getLocalName().equals("consumer")) {
                String ref = element.getAttribute("ref");
                String execnum = element.getAttribute("execnum");
                String interfac = element.getAttribute("interface");
                if (!interfac.isEmpty() && !execnum.isEmpty() && !ref.isEmpty()) {
                    bean.setFactoryMethod("exec");
                    bean.addConstructorArgValue(interfac);
                    bean.addConstructorArgValue(Integer.valueOf(execnum));
                    bean.addConstructorArgReference(ref);
                    bean.addConstructorArgValue(createConsumerProperties(element));
                }
            } else if (element.getLocalName().equals("producer")) {
                String interfac = element.getAttribute("interface");
                if (!interfac.isEmpty()) {
                    bean.getBeanDefinition().setAutowireCandidate(true);
                    bean.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
                    bean.setLazyInit(false);
                    bean.setFactoryMethod("getKafkaProxy");
                    bean.addConstructorArgValue(new AsyncInvocationHandler(Class.forName(interfac),
                            createProducerProperties(element)));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static Properties createProducerProperties(Element element) {
        String bootstrapServers = element.getAttribute("bootstrapServers");
        String acks = element.getAttribute("acks");
        String retries = element.getAttribute("retries");
        String keySerializer = element.getAttribute("keySerializer");
        String valueSerializer = element.getAttribute("valueSerializer");

        return KafkaProperties.createProducerProperties(bootstrapServers, acks, retries, keySerializer, valueSerializer);
    }

    public static Properties createConsumerProperties(Element element) {
        String bootstrapServers = element.getAttribute("bootstrapServers");
        String autoCommit = element.getAttribute("autoCommit");
        String maxPollIntervalMs = element.getAttribute("maxPollIntervalMs");
        String maxPollRecords = element.getAttribute("maxPollRecords");
        String autoCommitIntervalMs = element.getAttribute("autoCommitIntervalMs");
        String keyDeserializer = element.getAttribute("keyDeserializer");
        String valueDeserializer = element.getAttribute("valueDeserializer");
        String groupId = element.getAttribute("groupId");

        return KafkaProperties.createConsumerProperties(bootstrapServers, autoCommit, maxPollIntervalMs, maxPollRecords,
                autoCommitIntervalMs, keyDeserializer, valueDeserializer, groupId);
    }

}
