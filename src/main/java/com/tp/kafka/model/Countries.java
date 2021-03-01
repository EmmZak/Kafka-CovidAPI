/*
 * ===================================================================
 * TP-Kafka
 * Authors: Delphin Rukundo
 *        & Emmanuel Zakaryan
 *        & Elyes Boufdil
 *        & Lucas Lernoud
 * ===================================================================
 */


package com.tp.kafka.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Countries")
public class Countries {

    /*@GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;*/
    @Id
    private String Country;
    private String CountryCode;
    private String Slug;
    private int NewConfirmed;
    private int TotalConfirmed;
    private int NewDeaths;
    private int TotalDeaths;
    private int NewRecovered;
    private int TotalRecovered;
    private Timestamp Datemaj;

    protected Countries() {
    }

    public Countries(String country, String countryCode, String slug, int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered, Timestamp datemaj) {
        Country = country;
        CountryCode = countryCode;
        Slug = slug;
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        NewDeaths = newDeaths;
        TotalDeaths = totalDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
        Datemaj = datemaj;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public int getNewConfirmed() {
        return NewConfirmed;
    }

    public void setNewConfirmed(int newConfirmed) {
        NewConfirmed = newConfirmed;
    }

    public int getTotalConfirmed() {
        return TotalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }

    public int getNewDeaths() {
        return NewDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        NewDeaths = newDeaths;
    }

    public int getTotalDeaths() {
        return TotalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        TotalDeaths = totalDeaths;
    }

    public int getNewRecovered() {
        return NewRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        NewRecovered = newRecovered;
    }

    public int getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    public Timestamp getDatemaj() {
        return Datemaj;
    }

    public void setDatemaj(Timestamp datemaj) {
        Datemaj = datemaj;
    }

    @Override
    public String toString() {
        return  " Country : '" + Country + '\n' +
                " CountryCode : '" + CountryCode + '\n' +
                " Slug : " + Slug + '\n' +
                " NewConfirmed : " + NewConfirmed +'\n' +
                " TotalConfirmed : " + TotalConfirmed +'\n' +
                " NewDeaths : " + NewDeaths +'\n' +
                " TotalDeaths : " + TotalDeaths +'\n' +
                " NewRecovered : " + NewRecovered +'\n' +
                " TotalRecovered : " + TotalRecovered +'\n' +
                " Datemaj : " + Datemaj +'\n';

    }
}
