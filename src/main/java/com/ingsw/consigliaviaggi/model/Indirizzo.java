package com.ingsw.consigliaviaggi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Indirizzo {

    private Long id;
    private String via;
    private int civico;
    private String city;

    public Indirizzo(){}

    public Indirizzo(String via, int civico, String city){
        this.via = via;
        this.civico = civico;
        this.city = city;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
