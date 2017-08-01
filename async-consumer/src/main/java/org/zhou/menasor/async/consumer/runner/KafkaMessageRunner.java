package org.zhou.menasor.async.consumer.runner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.zhou.menasor.async.consumer.service.MessageHandleService;
import org.zhou.menasor.async.serialization.dto.AsyncDTO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaMessageRunner implements Runnable {
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private KafkaConsumer consumer;
    private MessageHandleService service;
    private boolean autoCommit = true;

    public KafkaMessageRunner(Properties properties, String topicName, MessageHandleService service) {
        this.consumer = new KafkaConsumer(properties);
        this.consumer.subscribe(Arrays.asList(topicName));
        this.service = service;
        if ("false".equals(properties.get("enable.auto.commit"))) {
            autoCommit = false;
        }
    }

    public void run() {
        try {
            while (!closed.get()) {
                ConsumerRecords<String, AsyncDTO> records = this.consumer.poll(10000);
                for (ConsumerRecord<String, AsyncDTO> record : records) {
                    service.doAction(record.key(), record.value());
                    if (!autoCommit) {
                        Map<TopicPartition, OffsetAndMetadata> offset = new HashMap<TopicPartition, OffsetAndMetadata>();
                        offset.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset()));
                        consumer.commitSync(offset);
                    }
                }
            }
        } catch (WakeupException e) {
            if (!closed.get()) throw e;
        } finally {
            consumer.close();
        }
    }

    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}
