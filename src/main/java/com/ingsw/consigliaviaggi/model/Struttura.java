package com.ingsw.consigliaviaggi.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Struttura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descrizione;
    @OneToOne
    private Indirizzo indirizzo;
    private TipoStruttura categoria;
    private int prezzo;
    @ElementCollection
    private List<String> foto;
    @OneToMany
    private List<Recensione> recensioni;

    public Struttura(){}

    public Struttura(String nome){
        this.nome = nome;
    }//TEST

    public Struttura(String nome, String descrizione, Indirizzo indirizzo, TipoStruttura categoria, int rangeDiPrezzo, String foto){
        this.nome = nome;
        this.descrizione = descrizione;
        this.indirizzo = indirizzo;
        this.categoria = categoria;
        this.prezzo = rangeDiPrezzo;
        this.foto = new LinkedList<>();
        this.foto.add(foto);
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

    public TipoStruttura getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoStruttura categoria) {
        this.categoria = categoria;
    }

    public int getRangeDiPrezzo() {
        return prezzo;
    }

    public void setRangeDiPrezzo(int rangeDiPrezzo) {
        this.prezzo = rangeDiPrezzo;
    }

    public List<String> getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto.add(foto);
    }

    public void deleteFoto(String foto){
        this.foto.remove(foto);

    }
    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setFoto(List<String> foto) {
        this.foto = foto;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }




}

