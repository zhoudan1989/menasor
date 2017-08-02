package org.zhou.menasor.async.consumer.consumer.impl;

import org.zhou.menasor.async.consumer.consumer.Consumer;
import org.zhou.menasor.async.consumer.runner.KafkaMessageRunner;
import org.zhou.menasor.async.consumer.service.MessageHandleService;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaConsumer implements Consumer {

    private Properties properties;
    private String topic;
    private int executorNum;
    private MessageHandleService service;
    private ExecutorService threadPool;

    public KafkaConsumer(String topic, int executorNum, MessageHandleService service) {
        this(topic, executorNum, service, getProperties());
    }

    public KafkaConsumer(String topic, int executorNum, MessageHandleService service, Properties properties) {
        this.topic = topic;
        this.executorNum = executorNum;
        this.service = service;
        this.properties = properties;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(KafkaConsumer.class.getResourceAsStream("/kafkaConsumer.properties"));
        } catch (IOException e) {
        }
        return properties;
    }

    public void close() {
        threadPool.shutdownNow();
    }

    public void start() {
        threadPool = Executors.newFixedThreadPool(executorNum);
        while (executorNum-- > 0)
            threadPool.execute(new KafkaMessageRunner(properties, topic, service));
    }
}