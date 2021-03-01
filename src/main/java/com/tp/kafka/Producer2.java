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
public class Producer2 {

    private static final Logger logger = LoggerFactory.getLogger(Producer2.class);
    private static final String TOPIC = "Topic2";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String Command) {
         logger.info(String.format("#### -> Command to send -> %s", Command));
        kafkaTemplate.send(TOPIC, Command);
    }
}
