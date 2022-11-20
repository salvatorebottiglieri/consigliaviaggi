package com.ingsw.consigliaviaggi.model;


public class Filtri{

    private String nome;
    private String city;
    private TipoStruttura categoria;
    private Double latitudine;
    private Double longitudine;
    private int distanza;
    private int prezzo;

    public Double getLatitudine() {
        return latitudine;
    }

    public Double getLongitudine() {
        return longitudine;
    }

    public int getDistanza() {
        return distanza;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }

    public String getNome() {
        return nome;
    }

    public String getCity() {
        return city;
    }

    public TipoStruttura getCategoria() {
        return categoria;
    }


    public int getPrezzo() {
        return prezzo;
    }



}
