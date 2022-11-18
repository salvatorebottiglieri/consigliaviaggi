package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.interfaces.RecensioneDAO;
import com.ingsw.consigliaviaggi.interfaces.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.TipoRecensore;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ControllerCheckRank {


    private final UtenteDAO utenteDAO;
    private final RecensioneDAO recensioneDAO;

    public ControllerCheckRank(UtenteDAO utenteDAO, RecensioneDAO recensioneDAO) {
        this.utenteDAO = utenteDAO;
        this.recensioneDAO = recensioneDAO;
    }

    @Scheduled(cron = "0 0 12 * * ?")//il metodo parte ogni giorno a mezzanotte
    public void checkRankUtenti() {

        Iterable<Utente> utentiDelSistema = utenteDAO.findAll();

        for (Utente utente : utentiDelSistema) {

            TipoRecensore tipoRecensoreVecchio = utente.getRank();

            TipoRecensore tipoRecensoreAppenaCalcolato = calcolaRankUtente(utente);

            if (tipoRecensoreAppenaCalcolato != tipoRecensoreVecchio) {

                utente.setRank(tipoRecensoreAppenaCalcolato);
                utenteDAO.save(utente);
            }
        }
    }

    public TipoRecensore calcolaRankUtente(Utente utente) {

        List<Recensione> recensioniUtente = recensioneDAO.findAllByAutoreEquals(utente);
        int sommaLikes = 0;
        int sommaDislikes = 0;

        for (Recensione recensione : recensioniUtente) {

            sommaLikes = sommaLikes + recensione.getLikes();
            sommaDislikes = sommaDislikes + recensione.getDislikes();

        }

        if (sommaLikes >= (sommaDislikes * 5)) {
            return TipoRecensore.eccellente;
        } else if (sommaLikes >= (sommaDislikes * 2)) {
            return TipoRecensore.ottimo;
        } else if (sommaLikes >= sommaDislikes) {
            return TipoRecensore.buono;
        } else {
            return TipoRecensore.pessimo;
        }
    }
}
