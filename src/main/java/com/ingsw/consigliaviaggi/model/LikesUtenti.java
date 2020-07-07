package com.ingsw.consigliaviaggi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LikesUtenti {

    @Id
    private String nomeUtente;
    @ManyToOne
    @JoinColumn
    private Recensione recensione;

    public LikesUtenti() {
    }

    public LikesUtenti(String nomeUtente,Recensione recensione) {

        this.nomeUtente = nomeUtente;
        this.recensione = recensione;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
}