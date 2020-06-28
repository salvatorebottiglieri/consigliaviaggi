package com.ingsw.consigliaviaggi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Struttura {

    private Long id;
    private String nome;
    private String descrizione;
    private Indirizzo indirizzo;
    private TipoStruttura categoria;
    private int prezzo;
    private Set<String> foto;
    private List<Recensione> recensioni;

    public Struttura(){}

    public Struttura(String nome, String descrizione, Indirizzo indirizzo){
        this.nome = nome;
        this.descrizione = descrizione;
        this.indirizzo = indirizzo;
    }

    public Struttura(String nome, String descrizione, Indirizzo indirizzo, TipoStruttura categoria, int prezzo, String foto){
        this.nome = nome;
        this.descrizione = descrizione;
        this.indirizzo = indirizzo;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.foto = new HashSet<>();
        this.foto.add(foto);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
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

    @ElementCollection
    public Set<String> getFoto() {
        return foto;
    }

    public void setFoto(Set<String> foto) {
        this.foto.addAll(foto);
    }

    public void deleteFoto(String foto){
        this.foto.remove(foto);
    }
    @OneToOne(cascade=CascadeType.ALL)
    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }



    @OneToMany(mappedBy = "struttura")
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



}

