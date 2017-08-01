package org.zhou.menasor.menasor.schema;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zhou.menasor.menasor.properties.KafkaProperties;
import org.zhou.menasor.menasor.proxy.AsyncProxyExecute;
import org.zhou.menasor.menasor.proxy.AsyncProxyFactory;

import java.util.Properties;

/**
 * Created by DT283 on 2017/7/4.
 */
public class AsyncRpcBeanDefinitionParser2 implements BeanDefinitionParser {
    private final static Logger logger = LogManager.getLogger(AsyncRpcBeanDefinitionParser2.class);

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

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setLazyInit(false);
        String id = element.getAttribute("id");
        if (element.getLocalName().equals("consumer")) {
            beanDefinition.setBeanClass(AsyncProxyExecute.class);
            String ref = element.getAttribute("ref");
            String execnum = element.getAttribute("execnum");
            String interfac = element.getAttribute("interface");
            if (!interfac.isEmpty() && !execnum.isEmpty() && !ref.isEmpty()) {
                beanDefinition.setFactoryMethodName("exec");
                ConstructorArgumentValues cav = new ConstructorArgumentValues();
                cav.addGenericArgumentValue(interfac);
                cav.addGenericArgumentValue(Integer.valueOf(execnum));
                cav.addGenericArgumentValue(new RuntimeBeanNameReference(ref));
                cav.addGenericArgumentValue(createConsumerProperties(element));
                beanDefinition.setConstructorArgumentValues(cav);
            }
        } else if (element.getLocalName().equals("producer")) {
            beanDefinition.setBeanClass(AsyncProxyFactory.class);
        }
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        return null;
    }
}
