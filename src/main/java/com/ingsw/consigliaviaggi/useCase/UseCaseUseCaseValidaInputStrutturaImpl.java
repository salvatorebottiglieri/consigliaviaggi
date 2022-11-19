package com.ingsw.consigliaviaggi.useCase;

import com.ingsw.consigliaviaggi.interfaces.UseCaseValidaInputStruttura;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Recensione;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UseCaseUseCaseValidaInputStrutturaImpl implements UseCaseValidaInputStruttura {

    @Override
    public boolean isValidName(@NotNull String name) {
        int maxNome = 20;
        return name.length() <= maxNome && !name.isEmpty();
    }

    @Override
    public boolean isValidDescription(@NotNull String description) {
        int maxDescrizione = 500;
        return description.length() <= maxDescrizione && !description.isEmpty();
    }

    @Override
    public boolean isValidAddress(@NotNull Indirizzo address) {
        String via = address.getVia();
        int civico = address.getCivico();
        String city = address.getCity();
        int maxVia = 50;
        int maxCity = 50;
        return via.length() <= maxVia && !(via.isEmpty()) &&
                city.length() <= maxCity && !(city.isEmpty()) && civico > 0;
    }

    @Override
    public boolean isValidPrice(@NotNull int price) {
        return price>=0 && price<=5;
    }

    @Override
    public boolean isValidRecensione(@NotNull Recensione recensione) {
        int MAXLENGTH = 250;
        String descrizione = recensione.getDescrizione();
        return descrizione.length() <= MAXLENGTH && !descrizione.isEmpty();
    }
}
