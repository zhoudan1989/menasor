package org.zhou.menasor.menasor.schema;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zhou.menasor.menasor.properties.ConsumerProperties;
import org.zhou.menasor.menasor.properties.ProducerProperties;
import org.zhou.menasor.menasor.proxy.AsyncProxyExecute;
import org.zhou.menasor.menasor.proxy.AsyncProxyFactory;

/**
 * Created by DT283 on 2017/7/4.
 */
public class AsyncRpcBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    private final static Logger logger = LogManager.getLogger(AsyncRpcBeanDefinitionParser.class);

    private int i = 0;

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
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder bean) {
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
                    RootBeanDefinition consumer = createConsumer(element);
                    String beanName = "consumer" + i++;
                    parserContext.getRegistry().registerBeanDefinition(beanName, consumer);
                    bean.addConstructorArgReference(beanName);
                }
            } else if (element.getLocalName().equals("producer")) {
                String interfac = element.getAttribute("interface");
                if (!interfac.isEmpty()) {
                    /*bean.getBeanDefinition().setAutowireCandidate(true);
                    bean.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
                    bean.setLazyInit(false);*/
                    bean.setFactoryMethod("getKafkaProxy");
                    String beanName = "producer" + i++;
                    RootBeanDefinition producer = createProducer(element);
                    parserContext.getRegistry().registerBeanDefinition(beanName, producer);
                    bean.addConstructorArgValue(Class.forName(interfac));
                    bean.addConstructorArgReference(beanName);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public RootBeanDefinition createProducer(Element element) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(ProducerProperties.class);
        beanDefinition.setLazyInit(false);
        beanDefinition.getPropertyValues().addPropertyValue("bootstrapServers",
                element.getAttribute("bootstrapServers"));
        beanDefinition.getPropertyValues().addPropertyValue("acks",
                element.getAttribute("acks"));
        beanDefinition.getPropertyValues().addPropertyValue("retries",
                element.getAttribute("retries"));
        beanDefinition.getPropertyValues().addPropertyValue("keySerializer",
                element.getAttribute("keySerializer"));
        beanDefinition.getPropertyValues().addPropertyValue("valueSerializer",
                element.getAttribute("valueSerializer"));
        return beanDefinition;
    }

    public RootBeanDefinition createConsumer(Element element) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(ConsumerProperties.class);
        beanDefinition.setLazyInit(false);
        beanDefinition.getPropertyValues().addPropertyValue("bootstrapServers",
                element.getAttribute("bootstrapServers"));
        beanDefinition.getPropertyValues().addPropertyValue("autoCommit",
                element.getAttribute("autoCommit"));
        beanDefinition.getPropertyValues().addPropertyValue("maxPollIntervalMs",
                element.getAttribute("maxPollIntervalMs"));
        beanDefinition.getPropertyValues().addPropertyValue("maxPollRecords",
                element.getAttribute("maxPollRecords"));
        beanDefinition.getPropertyValues().addPropertyValue("autoCommitIntervalMs",
                element.getAttribute("autoCommitIntervalMs"));
        beanDefinition.getPropertyValues().addPropertyValue("keyDeserializer",
                element.getAttribute("keyDeserializer"));
        beanDefinition.getPropertyValues().addPropertyValue("valueDeserializer",
                element.getAttribute("valueDeserializer"));
        beanDefinition.getPropertyValues().addPropertyValue("groupId",
                element.getAttribute("groupId"));
        return beanDefinition;
    }
}
