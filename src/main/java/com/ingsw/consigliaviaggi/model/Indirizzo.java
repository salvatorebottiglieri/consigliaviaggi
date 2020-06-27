package com.ingsw.consigliaviaggi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String via;
    private int civico;
    private String city;

    public Indirizzo(){}

    public Indirizzo(String via, int civico, String city){
        this.via = via;
        this.civico = civico;
        this.city = city;
    }


    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
