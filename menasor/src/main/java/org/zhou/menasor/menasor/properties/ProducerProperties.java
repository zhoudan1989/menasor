package org.zhou.menasor.menasor.properties;

import java.util.Properties;

/**
 * Created by DT283 on 2017/8/2.
 */
public class ProducerProperties {
    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    public ProducerProperties setBootstrapServers(String bootstrapServers) {
        properties.put("bootstrap.servers", bootstrapServers);
        return this;
    }

    public ProducerProperties setAcks(String acks) {
        properties.put("acks", acks);
        return this;
    }

    public ProducerProperties setRetries(String retries) {
        properties.put("retries", retries);
        return this;
    }

    public ProducerProperties setKeySerializer(String keySerializer) {
        properties.put("key.serializer", keySerializer);
        return this;
    }

    public ProducerProperties setValueSerializer(String valueSerializer) {
        properties.put("value.serializer", valueSerializer);
        return this;
    }
}
