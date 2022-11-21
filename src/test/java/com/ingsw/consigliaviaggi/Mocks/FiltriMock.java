package com.ingsw.consigliaviaggi.Mocks;

import com.ingsw.consigliaviaggi.model.Filtri;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;

import java.util.ArrayList;
import java.util.List;

public class FiltriMock extends Filtri {
    public FiltriMock(String name, String value) {
        super(name, value);
    }

    @Override
    public List<Struttura> research() {
        if(getNome().equalsIgnoreCase("NAME")){
            return mockResearchName(getValue());
        }
        else if(getNome().equalsIgnoreCase("PRICE")){
            return mockResearchPrice(getValue());
        }
        else if(getNome().equalsIgnoreCase("CATEGORY")){
            return mockResearchTipoStruttura(getValue());
        }
        return null;
    }

    public List<Struttura> mockResearchName(String value){
        Struttura struttura = struttura = new Struttura(value," some description",
                new Indirizzo("some via",1," some city")
                , TipoStruttura.hotel,0,"some image");
        List<Struttura>strutture = new ArrayList<>();
        strutture.add(struttura);
        return strutture;
    }
    public List<Struttura> mockResearchPrice(String value){
        Struttura struttura = new Struttura("some name","some descr", new Indirizzo("some via",1,"some ciy")
                , TipoStruttura.hotel,Integer.parseInt(value),"some path");
        List<Struttura>strutture = new ArrayList<>();
        strutture.add(struttura);
        return strutture;
    }
    public List<Struttura> mockResearchTipoStruttura(String value){
        Struttura struttura = new Struttura(value,"some descr", new Indirizzo("some via",1,"some ciy")
                , TipoStruttura.valueOf(value),0,"some path");
        List<Struttura>strutture = new ArrayList<>();
        strutture.add(struttura);
        return strutture;
    }
}
