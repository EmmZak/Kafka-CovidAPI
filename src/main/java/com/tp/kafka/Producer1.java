/*
 * ===================================================================
 * TP-Kafka
 * Authors: Delphin Rukundo
 *        & Emmanuel Zakaryan
 *        & Elyes Boufdil
 *        & Lucas Lernoud
 * ===================================================================
 */

package com.tp.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer1 {

    private static final Logger logger = LoggerFactory.getLogger(Producer1.class);
    private static final String TOPIC = "Topic1";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String Covid19Json) {
        logger.info(String.format("#### -> Message to send -> %s", Covid19Json));
        kafkaTemplate.send(TOPIC, Covid19Json);
    }
}
