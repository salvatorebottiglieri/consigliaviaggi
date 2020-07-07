package com.ingsw.consigliaviaggi.controllers;


import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.regex.Pattern;

import static com.ingsw.consigliaviaggi.model.VisibilitaRecensori.NOMEUTENTE;

@RestController
public class ControllerRegistrazione {

    private final UtenteDAO utenteDAO;

    @Autowired
    private ControllerValidazioneInput controllerValidazioneInput;

    public ControllerRegistrazione(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @PostMapping(path = "/all/registrazione", consumes = "application/json", produces = "application/json")
    public Utente aggiungiUtente(@RequestBody Utente nuovoUtente){

        if(controllerValidazioneInput.isValidRegistrazione(nuovoUtente)) {

            if (!utenteDAO.existsByNomeUtente(nuovoUtente.getNomeUtente())) {

                nuovoUtente.setActive(true);
                nuovoUtente.setMostraCome(NOMEUTENTE);
                utenteDAO.save(nuovoUtente);
                return nuovoUtente;
            }
        }
        return null;//lancia eccezione


    }


}