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

import com.tp.kafka.model.Countries;
import com.tp.kafka.model.Global;
import com.tp.kafka.repository.CountryRepository;
import com.tp.kafka.repository.GlobalRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer2 {
    private final Logger log = LoggerFactory.getLogger(Consumer2.class);

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private GlobalRepository globalRepository;
    @Autowired
    private Producer3 p3;
    
    // Topic2 = Commands from Console
    @KafkaListener(topics = "Topic2", groupId = "covid_id")
    public void consume(String command) throws IOException {
    	
    	int nbCountries = countryRepository.findAll().size();

    	// log colors
		String RED = "\u001B[31m";
		String END = "\033[0m";

		// the first part of the command
        String firstPart = command.split(" ")[0];

    	switch(firstPart) {
    		case "Get_global_values":
    			Global globals = globalRepository.findById();
    			p3.sendMessage(globals.toString());
    			break;

    		case "Get_country_values":
				try {
					Countries country =  countryRepository.getValues(command.substring(firstPart.length() + 1, command.length()));
					p3.sendMessage(country.toString());
				} catch (Exception e) {
					System.out.println("########" +RED+" ERROR "  +END  +"#########");
					System.out.println("Pays Introuvable \n");
					System.out.print("Entrer votre commande: ");
				}
    			break;
    			
    		case "Get_confirmed_avg":
    			double confirmed_avg = (double) globalRepository.getTotalConfirmed()/nbCountries;

    			p3.sendMessage("Confirmed Average: " + confirmed_avg);
    			break;
    			
    		case "Get_deaths_avg":
    			double death_avg = (double) globalRepository.getTotalDeaths()/nbCountries;

	   			p3.sendMessage("Deaths Average: " + death_avg);
	   			break;
	   			
    		case "Get_countries_deaths_percent":
    			double totalDeaths = globalRepository.getTotalDeaths();
				double totalConfirmed = globalRepository.getTotalConfirmed();

    			double mortality = totalDeaths/totalConfirmed * 100.0;

   	   			p3.sendMessage("Countries Deaths Percent" + mortality);
   	   			break;
			default:
				System.out.println("Commande non reconnue! \n Tapez \"Help\" pour voir la liste de toutes les commandes possibles");
				System.out.print("Entrer votre commande: ");
				break;
    	}
    	
    }
}

