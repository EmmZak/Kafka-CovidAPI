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

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TpKafkaApplication {

    private static final Logger log = LoggerFactory.getLogger(TpKafkaApplication.class);
    private static final String COVID19API_ENDPOINT = "https://api.covid19api.com/summary";

    @Autowired
    private Producer2 Pr2;
    @Autowired
    private Producer1 Pr1;

    public static void main(String[] args) {
        SpringApplication.run(TpKafkaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo()  {
        return (args) ->  {
            ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
            // define task to run repeatedly
            Runnable task = () -> {
                try {
                    HttpResponse<String> jsonResponse = Unirest.get(COVID19API_ENDPOINT).asString();

                    if (jsonResponse.getStatus() == 200) {
                        String Covid19Json = jsonResponse.getBody();
                        Pr1.sendMessage(Covid19Json);
                    }
                } catch(Exception e) {
                    log.warn("Something went wrong!");
                    log.warn(String.valueOf(e));
                };

            };
            // repeat the task every 30 minutes
            ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task, 0, 30, TimeUnit.MINUTES);

            CommandLineInput();
        };
    }

    public void CommandLineInput() {

        // log colors
        String YELLOW = "\u001B[93m";
        String END = "\033[0m";

        System.out.println(
                "------------------------------Liste de commandes-----------------------------\n" +
                        "Get_global_values (retourne les valeurs globales clés Global du fichier json)\n" +
                        "Get_country_values v_pays (retourne les valeurs du pays demandé ou v_pays est une chaine de caractère du pays demandé)\n" +
                        "Get_confirmed_avg (retourne une moyenne des cas confirmés sum(pays)/nb(pays))\n" +
                        "Get_deaths_avg (retourne une moyenne des Décès sum(pays)/nb(pays))\n" +
                        "Get_countries_deaths_percent (retourne le pourcentage de Décès par rapport aux cas confirmés)\n" +
                        "Help (affiche la liste des commandes et leur explication) \n"
        );
        System.out.print("Entrer votre commande: ");
        while  (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                String[] commandParts = command.split(" ");

                switch (commandParts[0]) {
                    case "Help":
                    case "help":
                        System.out.println(
                                "------------------------------Liste de commandes-----------------------------\n" +
                                        "Get_global_values (retourne les valeurs globales clés Global du fichier json)\n" +
                                        "Get_country_values v_pays (retourne les valeurs du pays demandé ou v_pays est une chaine de caractère du pays demandé)\n" +
                                        "Get_confirmed_avg (retourne une moyenne des cas confirmés sum(pays)/nb(pays))\n" +
                                        "Get_deaths_avg (retourne une moyenne des Décès sum(pays)/nb(pays))\n" +
                                        "Get_countries_deaths_percent (retourne le pourcentage de Décès par rapport aux cas confirmés)\n" +
                                        "Help (affiche la liste des commandes et leur explication) \n"
                        );
                        System.out.print("Entrer votre commande: ");
                        break;
                    case "Get_global_values":
                        Pr2.sendMessage("Get_global_values");
                        break;
                    case "Get_country_values":
                        if (commandParts.length < 2) {
                            System.out.println(YELLOW + "Warning: " + END + "Commande  mal formulée!\nTapez \"Help\" pour voir la liste de toutes les commandes possibles \n");
                            System.out.print("Entrer votre commande: ");
                            break;
                        }
                        Pr2.sendMessage(command);
                        break;
                    case "Get_confirmed_avg":
                        Pr2.sendMessage("Get_confirmed_avg");
                        break;
                    case "Get_deaths_avg":
                        Pr2.sendMessage("Get_deaths_avg");
                        break;
                    case "Get_countries_deaths_percent":
                        Pr2.sendMessage("Get_countries_deaths_percent");
                        break;
                    default:
                        System.out.println(YELLOW + "Warning: " + END + "Commande non reconnue!\nTapez \"Help\" pour voir la liste de toutes les commandes possibles \n");
                        System.out.print("Entrer votre commande: ");
                        break;
                }
            } catch (Exception e) {
                log.warn("Switch error!");
                log.warn(String.valueOf(e));
                System.out.println(e);
            }

        }
    }
}
