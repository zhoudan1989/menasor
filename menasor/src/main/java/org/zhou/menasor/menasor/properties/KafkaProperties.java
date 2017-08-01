package org.zhou.menasor.menasor.properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * Created by DT283 on 2016/6/15.
 */
public class KafkaProperties {
    private final static Logger logger = LogManager.getLogger(KafkaProperties.class);

    public static Properties createConsumerProperties(String bootstrapServers, String autoCommit,
                                                      String maxPollIntervalMs, String maxPollRecords, String autoCommitIntervalMs,
                                                      String keyDeserializer, String valueDeserializer, String groupId) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("enable.auto.commit", autoCommit);
        properties.put("max.poll.interval.ms", maxPollIntervalMs);
        properties.put("max.poll.records", maxPollRecords);
        properties.put("auto.commit.interval.ms", autoCommitIntervalMs);
        properties.put("key.deserializer", keyDeserializer);
        properties.put("value.deserializer", valueDeserializer);
        properties.put("group.id", groupId);
        return properties;
    }

    public static Properties createProducerProperties(String bootstrapServers, String acks,
                                                      String retries, String keySerializer,
                                                      String valueSerializer) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("acks", acks);
        properties.put("retries", retries);
        properties.put("key.serializer", keySerializer);
        properties.put("value.serializer", valueSerializer);
        return properties;
    }
}
