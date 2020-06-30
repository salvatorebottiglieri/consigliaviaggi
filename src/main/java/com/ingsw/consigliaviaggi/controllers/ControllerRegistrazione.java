package com.ingsw.consigliaviaggi.controllers;


import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerRegistrazione {

    private final UtenteDAO utenteDAO;

    public ControllerRegistrazione(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @PostMapping(path = "/registrazione", consumes = "application/json", produces = "application/json")
    public boolean aggiungiUtente(@RequestBody Utente nuovoUtente){

        if(!utenteDAO.existsByNomeUtente(nuovoUtente.getNomeUtente())) {
            utenteDAO.save(nuovoUtente);
            return true;
        }
        return false;


    }

}


