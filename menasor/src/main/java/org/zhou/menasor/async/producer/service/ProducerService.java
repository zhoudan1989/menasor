package org.zhou.menasor.async.producer.service;

import org.zhou.menasor.async.serialization.dto.AsyncDTO;

public interface ProducerService {

    void sendMessage(String topic, String KafkaKey, AsyncDTO kafkaInfo);

}





