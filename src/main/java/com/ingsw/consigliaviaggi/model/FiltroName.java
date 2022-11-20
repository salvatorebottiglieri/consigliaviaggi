package com.ingsw.consigliaviaggi.model;

import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;

import java.util.List;

public class FiltroName extends Filtri{

    public FiltroName(String name, String value) {
        super(name, value);
    }

    @Override
    public List<Struttura> research() {
        String value = getValue();
        return strutturaDAO.findByNome(value);
    }

    public String getValue() {
        return super.getValue();
    }
}
