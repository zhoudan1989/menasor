package org.zhou.menasor.menasor.properties;

import java.util.Properties;

/**
 * Created by DT283 on 2017/8/2.
 */
public class ConsumerProperties {
    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    public ConsumerProperties setBootstrapServers(String bootstrapServers) {
        properties.put("bootstrap.servers", bootstrapServers);
        return this;
    }

    public ConsumerProperties setAutoCommit(String autoCommit) {
        properties.put("enable.auto.commit", autoCommit);
        return this;
    }

    public ConsumerProperties setMaxPollIntervalMs(String maxPollIntervalMs) {
        properties.put("max.poll.interval.ms", maxPollIntervalMs);
        return this;
    }

    public ConsumerProperties setMaxPollRecords(String maxPollRecords) {
        properties.put("max.poll.records", maxPollRecords);
        return this;
    }

    public ConsumerProperties setAutoCommitIntervalMs(String autoCommitIntervalMs) {
        properties.put("auto.commit.interval.ms", autoCommitIntervalMs);
        return this;
    }

    public ConsumerProperties setKeyDeserializer(String keyDeserializer) {
        properties.put("key.deserializer", keyDeserializer);
        return this;
    }

    public ConsumerProperties setValueDeserializer(String valueDeserializer) {
        properties.put("value.deserializer", valueDeserializer);
        return this;
    }

    public ConsumerProperties setGroupId(String groupId) {
        properties.put("group.id", groupId);
        return this;
    }
}
