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
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class Consumer1 {
    private final Logger log = LoggerFactory.getLogger(Producer1.class);

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private GlobalRepository globalRepository;

    @KafkaListener(topics = "Topic1", groupId = "covid_id")
    public void consume(JSONObject Covid19Json) throws IOException {

        JSONObject Global = Covid19Json.getJSONObject("Global");
        JSONArray countries = Covid19Json.getJSONArray("Countries");        


        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = null;

        try {
            Date d = input.parse(Covid19Json.getString("Date"));
            timeStamp = output.format(d);
        } catch (Exception e) {
            log.warn("Something went wrong!");
            log.warn(String.valueOf(e));
        };

        String finalTimeStamp = timeStamp;
        countries.forEach(country -> {
            JSONObject countryObj = (JSONObject) country;

            // save countries in the Database
            countryRepository.save(new Countries(
                    countryObj.getString("Country"),
                    countryObj.getString("CountryCode"),
                    countryObj.getString("Slug"),
                    countryObj.getInt("NewConfirmed"),
                    countryObj.getInt("TotalConfirmed"),
                    countryObj.getInt("NewDeaths"),
                    countryObj.getInt("TotalDeaths"),
                    countryObj.getInt("NewRecovered"),
                    countryObj.getInt("TotalRecovered"),
                    Timestamp.valueOf(finalTimeStamp)
            ));
        });


        // save Global object in the database
        globalRepository.save(new Global(
                1,
                Global.getInt("NewConfirmed"),
                Global.getInt("TotalConfirmed"),
                Global.getInt("NewDeaths"),
                Global.getInt("TotalDeaths"),
                Global.getInt("NewRecovered"),
                Global.getInt("TotalRecovered"),
                Timestamp.valueOf(finalTimeStamp)
        ));

        log.info(String.format("#### -> Consumed message -> %s", Covid19Json));
        log.info("#### -> Global -> {}", Global);
        log.info("#### -> Countries -> {}", countries);

    }
}
