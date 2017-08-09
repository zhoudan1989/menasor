package org.zhou.menasor.menasor.config;

import java.util.Properties;

/**
 * Created by DT283 on 2017/8/2.
 */
public class ConsumerConfig {
    private Properties properties = new Properties();
    private Object ref;
    private Integer execnum;
    private String interfac;

    public Properties getProperties() {
        return properties;
    }

    public ConsumerConfig setBootstrapServers(String bootstrapServers) {
        properties.put("bootstrap.servers", bootstrapServers);
        return this;
    }

    public ConsumerConfig setAutoCommit(String autoCommit) {
        properties.put("enable.auto.commit", autoCommit);
        return this;
    }

    public ConsumerConfig setMaxPollIntervalMs(String maxPollIntervalMs) {
        properties.put("max.poll.interval.ms", maxPollIntervalMs);
        return this;
    }

    public ConsumerConfig setMaxPollRecords(String maxPollRecords) {
        properties.put("max.poll.records", maxPollRecords);
        return this;
    }

    public ConsumerConfig setAutoCommitIntervalMs(String autoCommitIntervalMs) {
        properties.put("auto.commit.interval.ms", autoCommitIntervalMs);
        return this;
    }

    public ConsumerConfig setKeyDeserializer(String keyDeserializer) {
        properties.put("key.deserializer", keyDeserializer);
        return this;
    }

    public ConsumerConfig setValueDeserializer(String valueDeserializer) {
        properties.put("value.deserializer", valueDeserializer);
        return this;
    }

    public ConsumerConfig setGroupId(String groupId) {
        properties.put("group.id", groupId);
        return this;
    }

    public Object getRef() {
        return ref;
    }

    public ConsumerConfig setRef(Object ref) {
        this.ref = ref;
        return this;
    }

    public Integer getExecnum() {
        return execnum;
    }

    public ConsumerConfig setExecnum(Integer execnum) {
        this.execnum = execnum;
        return this;
    }

    public String getInterfac() {
        return interfac;
    }

    public ConsumerConfig setInterfac(String interfac) {
        this.interfac = interfac;
        return this;
    }
}
