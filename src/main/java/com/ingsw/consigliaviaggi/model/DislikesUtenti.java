package com.ingsw.consigliaviaggi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DislikesUtenti {

    @Id
    private String nomeUtente;
    @ManyToOne
    @JoinColumn
    private Recensione recensione;

    public DislikesUtenti(){}

    public DislikesUtenti(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
}