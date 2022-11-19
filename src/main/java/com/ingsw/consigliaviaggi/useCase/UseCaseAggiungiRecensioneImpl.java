package com.ingsw.consigliaviaggi.useCase;

import com.ingsw.consigliaviaggi.interfaces.RecensioneDAO;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.interfaces.UseCaseAggiungiRecensione;
import com.ingsw.consigliaviaggi.interfaces.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class UseCaseAggiungiRecensioneImpl implements UseCaseAggiungiRecensione {

    UtenteDAO utenteDAO;
    StrutturaDAO strutturaDAO;
    RecensioneDAO recensioneDAO;

    public UseCaseAggiungiRecensioneImpl(UtenteDAO utenteDAO, StrutturaDAO strutturaDAO, RecensioneDAO recensioneDAO) {
        this.utenteDAO = utenteDAO;
        this.strutturaDAO = strutturaDAO;
        this.recensioneDAO = recensioneDAO;
    }

    @Override
    public boolean aggiungiRecensione(Recensione recensione, String idStruttura, String nomeUtente) {
        Optional<Utente> utenteOptional = utenteDAO.findByNomeUtente(nomeUtente);
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(idStruttura);
        if (utenteOptional.isPresent() && strutturaOptional.isPresent()) {
            Utente utente = utenteOptional.get();
            Struttura struttura = strutturaOptional.get();
            recensione.setAutore(utente);
            recensione.setStruttura(struttura);
            recensione.setDataDiAggiunta(new Date());
            recensioneDAO.save(recensione);
            return true;
        }
        throw new NoSuchElementException("struttura o utente non presenti nel sistema");
    }
}
