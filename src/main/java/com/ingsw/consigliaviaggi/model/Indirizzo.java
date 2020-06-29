package com.ingsw.consigliaviaggi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Indirizzo {

    private String id;
    private String via;
    private int civico;
    private String city;

    public Indirizzo(){}

    public Indirizzo(String via, int civico, String city){
        this.via = via;
        this.civico = civico;
        this.city = city;
        this.id =via+" "+civico+" "+city;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "Indirizzo{" +
                "id=" + id +
                ", via='" + via + '\'' +
                ", civico=" + civico +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Indirizzo indirizzo = (Indirizzo) o;
        return civico == indirizzo.civico &&
                via.equals(indirizzo.via) &&
                city.equals(indirizzo.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(via, civico, city);
    }
}
