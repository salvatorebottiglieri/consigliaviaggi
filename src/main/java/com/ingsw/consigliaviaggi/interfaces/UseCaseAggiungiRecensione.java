package com.ingsw.consigliaviaggi.interfaces;

import com.ingsw.consigliaviaggi.model.Recensione;

public interface UseCaseAggiungiRecensione {

    boolean aggiungiRecensione(Recensione recensione, String idStruttura, String nomeUtente);
}
