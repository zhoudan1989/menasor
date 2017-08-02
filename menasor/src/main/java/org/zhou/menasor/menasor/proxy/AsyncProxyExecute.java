package org.zhou.menasor.menasor.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zhou.menasor.async.consumer.consumer.Consumer;
import org.zhou.menasor.async.consumer.consumer.impl.KafkaConsumer;
import org.zhou.menasor.menasor.impl.AsyncHandleServiceImpl;
import org.zhou.menasor.menasor.properties.ConsumerProperties;

/**
 * Created by DT283 on 2017/7/3.
 */
public class AsyncProxyExecute {

    private final static Logger logger = LogManager.getLogger(AsyncProxyExecute.class);

    public static Consumer exec(String interfac, int executorNum, Object clazz, ConsumerProperties properties) {
        try {
            Consumer consumer = new KafkaConsumer(interfac,
                    executorNum,
                    new AsyncHandleServiceImpl(clazz),
                    properties.getProperties());
            consumer.start();
            return consumer;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
