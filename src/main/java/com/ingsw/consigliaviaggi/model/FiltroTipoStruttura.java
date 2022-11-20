package com.ingsw.consigliaviaggi.model;

import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;

import java.util.List;

public class FiltroTipoStruttura extends Filtri{

    public FiltroTipoStruttura(String name, String value) {
        super(name, value);
    }

    @Override
    public List<Struttura> research() {
        String value = getValue();
        return strutturaDAO.findByCategoria(value);
    }

    public String getValue(){
        return super.getValue();
    }
}
