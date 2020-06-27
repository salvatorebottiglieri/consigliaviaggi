package com.ingsw.consigliaviaggi.model;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Recensione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int voto;
    private String descrizione;
    private int like;
    private int dislike;
    private Date dataDiAggiunta;
    @ManyToOne
    private Utente autore;
    @ManyToOne
    private Struttura struttura;

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public Date getDataDiAggiunta() {
        return dataDiAggiunta;
    }

    public void setDataDiAggiunta(Date dataDiAggiunta) {
        this.dataDiAggiunta = dataDiAggiunta;
    }

    public Utente getAutore() {
        return autore;
    }

    public void setAutore(Utente autore) {
        this.autore = autore;
    }

    public Struttura getStruttura() {
        return struttura;
    }

    public void setStruttura(Struttura struttura) {
        this.struttura = struttura;
    }
}
