package org.zhou.menasor.async.producer.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zhou.menasor.async.producer.service.ProducerService;
import org.zhou.menasor.async.serialization.dto.AsyncDTO;

import java.io.IOException;
import java.util.Properties;

public class KafkaInfoServiceImpl implements ProducerService {
    private final static Logger logger = LogManager.getLogger(KafkaInfoServiceImpl.class);

    private Producer<String, AsyncDTO> producer;

    public KafkaInfoServiceImpl(Properties properties) {
        producer = new KafkaProducer<String, AsyncDTO>(properties);
    }

    public KafkaInfoServiceImpl() {
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("/kafkaProducer.properties"));
            producer = new KafkaProducer<String, AsyncDTO>(properties);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void sendMessage(String topic, String kafkaKey, AsyncDTO kafkaInfo) {
        if (kafkaInfo != null) {
            Callback callback = new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    CallbackLog log = new CallbackLog().setTimestamp(metadata.timestamp()).setTopic(metadata.topic());
                    logger.info(JSON.toJSON(log));
                    if (exception != null) {
                        logger.error(exception);
                    }
                }
            };
            producer.send(new ProducerRecord<String, AsyncDTO>(topic, kafkaKey, kafkaInfo), callback);
        }
    }

    private class CallbackLog {
        private long timestamp;
        private String topic;

        public long getTimestamp() {
            return timestamp;
        }

        public CallbackLog setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public String getTopic() {
            return topic;
        }

        public CallbackLog setTopic(String topic) {
            this.topic = topic;
            return this;
        }
    }
}





