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

import com.tp.kafka.repository.CountryRepository;
import com.tp.kafka.repository.GlobalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer3 {
    private final Logger log = LoggerFactory.getLogger(Consumer3.class);

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private GlobalRepository globalRepository;

    // log colors
    String GREEN = "\u001B[32m";
    String END = "\033[0m";

    @KafkaListener(topics = "Topic3", groupId = "covid_id")
    public void consume(String reponse) throws IOException {
        System.out.println("########" + GREEN + " SUCCESS "  + END  +"#########");
    	System.out.println(reponse + "\n");
        System.out.print("Entrer votre commande: ");
    }


}

