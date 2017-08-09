package org.zhou.menasor.menasor.config;

import java.util.Properties;

/**
 * Created by DT283 on 2017/8/2.
 */
public class ProducerConfig {
    private Properties properties = new Properties();
    private String interfac;

    public Properties getProperties() {
        return properties;
    }

    public ProducerConfig setBootstrapServers(String bootstrapServers) {
        properties.put("bootstrap.servers", bootstrapServers);
        return this;
    }

    public ProducerConfig setAcks(String acks) {
        properties.put("acks", acks);
        return this;
    }

    public ProducerConfig setRetries(String retries) {
        properties.put("retries", retries);
        return this;
    }

    public ProducerConfig setKeySerializer(String keySerializer) {
        properties.put("key.serializer", keySerializer);
        return this;
    }

    public ProducerConfig setValueSerializer(String valueSerializer) {
        properties.put("value.serializer", valueSerializer);
        return this;
    }

    public String getInterfac() {
        return interfac;
    }

    public ProducerConfig setInterfac(String interfac) {
        this.interfac = interfac;
        return this;
    }
}
