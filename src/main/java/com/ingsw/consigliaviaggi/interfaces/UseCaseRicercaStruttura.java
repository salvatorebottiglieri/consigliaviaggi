package com.ingsw.consigliaviaggi.interfaces;

import com.ingsw.consigliaviaggi.model.Filtri;
import com.ingsw.consigliaviaggi.model.Struttura;
import java.util.List;

public interface UseCaseRicercaStruttura {

    public List<Struttura> findStrutture(List<Filtri> filtri);
    public Struttura getStruttura(String strutturaID);
}
