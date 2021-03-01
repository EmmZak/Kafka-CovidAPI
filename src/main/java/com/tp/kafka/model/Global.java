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
@Table(name = "Global")
public class Global {

    @Id
    private int id;
    private int NewConfirmed;
    private int TotalConfirmed;
    private int NewDeaths;
    private int TotalDeaths;
    private int NewRecovered;
    private int TotalRecovered;
    private Timestamp  Datemaj;

    public Global(int id, int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered, Timestamp datemaj) {
        this.id = id;
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        NewDeaths = newDeaths;
        TotalDeaths = totalDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
        Datemaj = datemaj;
    }

    protected Global() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return  " NewConfirmed: " + NewConfirmed + "\n" +
                " TotalConfirmed: " + TotalConfirmed + "\n"+
                " NewDeaths: " + NewDeaths + "\n"+
                " TotalDeaths: " + TotalDeaths + "\n"+
                " NewRecovered: " + NewRecovered + "\n"+
                " TotalRecovered: " + TotalRecovered + "\n"+
                " Datemaj: " + Datemaj + "\n";
    }
}
