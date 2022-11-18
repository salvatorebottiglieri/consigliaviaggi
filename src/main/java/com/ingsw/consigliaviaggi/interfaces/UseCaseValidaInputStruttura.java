package com.ingsw.consigliaviaggi.interfaces;

import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Recensione;

public interface UseCaseValidaInputStruttura {
    boolean isValidName(String name);
    boolean isValidDescription(String description);
    boolean isValidAddress(Indirizzo address);
    boolean isValidPrice(int price);
    boolean isValidRecensione(Recensione recensione);
}
