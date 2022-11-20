package com.ingsw.consigliaviaggi.model;

import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;

import java.util.List;

public class FiltroPrezzo extends Filtri{
    public FiltroPrezzo(String name, String value) {
        super(name, value);
    }

    @Override
    public List<Struttura> research() {
        int value = Integer.parseInt(getValue());
        return strutturaDAO.findByPrezzo(value);
    }

    public String getValue() {
        return super.getValue();
    }
}
