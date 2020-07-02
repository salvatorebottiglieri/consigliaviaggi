package com.ingsw.consigliaviaggi.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Utente {

    @Id
    private String nomeUtente;
    private String nome;
    private String cognome;
    private String indirizzoEmail;
    private String password;
    private Gender sesso;
    private String city;
    private Date dataDiNascita;
    private TipoRecensore rank;
    private VisibilitaRecensori mostraCome;
    @OneToMany(mappedBy = "autore")
    private List<Recensione> recensioni;

    public Utente(){}

    public Utente(String nomeUtente, String nome, String cognome, String indirizzoEmail, String password, Gender sesso, String city, Date dataDiNascita) {
        this.nomeUtente = nomeUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzoEmail = indirizzoEmail;
        this.password = password;
        this.sesso = sesso;
        this.city = city;
        this.dataDiNascita = dataDiNascita;
    }


    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzoEmail() {
        return indirizzoEmail;
    }

    public void setIndirizzoEmail(String indirizzoEmail) {
        this.indirizzoEmail = indirizzoEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    public Gender getSesso() {
        return sesso;
    }

    public void setSesso(Gender sesso) {
        this.sesso = sesso;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    @Enumerated(EnumType.STRING)
    public TipoRecensore getRank() {
        return rank;
    }

    public void setRank(TipoRecensore rank) {
        this.rank = rank;
    }

    @Enumerated(EnumType.STRING)
    public VisibilitaRecensori getMostraCome() {
        return mostraCome;
    }

    public void setMostraCome(VisibilitaRecensori mostraCome) {
        this.mostraCome = mostraCome;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public Utente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public boolean isUtente(){return true;}

    public void addRecensione(Recensione recensione){}




}
