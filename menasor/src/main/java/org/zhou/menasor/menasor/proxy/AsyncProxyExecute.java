package org.zhou.menasor.menasor.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zhou.menasor.async.consumer.consumer.Consumer;
import org.zhou.menasor.async.consumer.consumer.impl.KafkaConsumer;
import org.zhou.menasor.menasor.impl.AsyncHandleServiceImpl;
import org.zhou.menasor.menasor.config.ConsumerConfig;

/**
 * Created by DT283 on 2017/7/3.
 */
public class AsyncProxyExecute {

    private final static Logger logger = LogManager.getLogger(AsyncProxyExecute.class);

    public static Consumer exec(ConsumerConfig config) {
        try {
            Consumer consumer = new KafkaConsumer(config.getInterfac(),
                    config.getExecnum(),
                    new AsyncHandleServiceImpl(config.getRef()),
                    config.getProperties());
            consumer.start();
            return consumer;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
