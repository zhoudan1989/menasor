package org.zhou.menasor.menasor.schema;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zhou.menasor.menasor.config.ConsumerConfig;
import org.zhou.menasor.menasor.config.ProducerConfig;
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
                    BeanDefinitionBuilder consumer = createConsumer(element);
                    String beanName = "consumer" + i++;
                    parserContext.getRegistry().registerBeanDefinition(beanName, consumer.getBeanDefinition());
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
                    BeanDefinitionBuilder producer = createProducer(element);
                    parserContext.getRegistry().registerBeanDefinition(beanName, producer.getBeanDefinition());
                    bean.addConstructorArgReference(beanName);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public BeanDefinitionBuilder createProducer(Element element) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(ProducerConfig.class.getName());
        builder.setLazyInit(false);
        builder.addPropertyValue("bootstrapServers",
                element.getAttribute("bootstrapServers"));
        builder.addPropertyValue("acks",
                element.getAttribute("acks"));
        builder.addPropertyValue("retries",
                element.getAttribute("retries"));
        builder.addPropertyValue("keySerializer",
                element.getAttribute("keySerializer"));
        builder.addPropertyValue("valueSerializer",
                element.getAttribute("valueSerializer"));
        builder.addPropertyValue("valueSerializer",
                element.getAttribute("valueSerializer"));
        builder.addPropertyValue("interfac",
                element.getAttribute("interface"));
        return builder;
    }

    public BeanDefinitionBuilder createConsumer(Element element) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(ConsumerConfig.class.getName());
        builder.setLazyInit(false);
        builder.addPropertyValue("bootstrapServers",
                element.getAttribute("bootstrapServers"));
        builder.addPropertyValue("autoCommit",
                element.getAttribute("autoCommit"));
        builder.addPropertyValue("maxPollIntervalMs",
                element.getAttribute("maxPollIntervalMs"));
        builder.addPropertyValue("maxPollRecords",
                element.getAttribute("maxPollRecords"));
        builder.addPropertyValue("autoCommitIntervalMs",
                element.getAttribute("autoCommitIntervalMs"));
        builder.addPropertyValue("keyDeserializer",
                element.getAttribute("keyDeserializer"));
        builder.addPropertyValue("valueDeserializer",
                element.getAttribute("valueDeserializer"));
        builder.addPropertyValue("groupId",
                element.getAttribute("groupId"));
        builder.addPropertyReference("ref",element.getAttribute("ref"));
        builder.addPropertyValue("execnum",
                element.getAttribute("execnum"));
        builder.addPropertyValue("interfac",
                element.getAttribute("interface"));
        return builder;
    }
}
