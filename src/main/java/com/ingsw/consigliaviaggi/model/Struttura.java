package com.ingsw.consigliaviaggi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class Struttura {

    private String id;
    private String nome;
    private String descrizione;
    private Indirizzo indirizzo;
    private TipoStruttura categoria;
    private int prezzo;
    private String foto;
    private List<Recensione> recensioni;

    public Struttura(){}

    public Struttura( String nome, String descrizione, Indirizzo indirizzo, TipoStruttura categoria, int prezzo, String immagine){
        this.nome = nome;
        this.descrizione = descrizione;
        this.indirizzo = indirizzo;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.foto = immagine;
        this.id = nome+"-"+indirizzo.getVia()+"-"+indirizzo.getCivico()+"-"+indirizzo.getCity();
    }

    @Id
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Enumerated(EnumType.STRING)
    public TipoStruttura getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoStruttura categoria) {
        this.categoria = categoria;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;}


    @OneToOne(cascade=CascadeType.ALL)
    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }


    @OneToMany(mappedBy = "struttura")
    @JsonIgnore
    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "Struttura{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", indirizzo=" + indirizzo +
                ", categoria=" + categoria +
                ", prezzo=" + prezzo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Struttura struttura = (Struttura) o;
        return nome.equals(struttura.nome) &&
                indirizzo.equals(struttura.indirizzo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, indirizzo);
    }


}

