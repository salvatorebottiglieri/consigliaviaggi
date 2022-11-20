package com.ingsw.consigliaviaggi.model;


import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class Filtri{
    private String name;
    StrutturaDAO strutturaDAO ;
    private String value;

    public Filtri(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getNome() {
        return name;
    }
    public abstract List<Struttura> research();

}
