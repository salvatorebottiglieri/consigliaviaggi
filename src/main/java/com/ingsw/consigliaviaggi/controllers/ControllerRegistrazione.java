package com.ingsw.consigliaviaggi.controllers;


import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.exception.ElementIsAlreadyPresentExcetpion;
import com.ingsw.consigliaviaggi.exception.NoValidInputException;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.ingsw.consigliaviaggi.model.VisibilitaRecensori.NOMEUTENTE;


@RestController
public class ControllerRegistrazione {

    private final UtenteDAO utenteDAO;


    private final ControllerValidazioneInput controllerValidazioneInput;

    public ControllerRegistrazione(UtenteDAO utenteDAO, ControllerValidazioneInput controllerValidazioneInput) {
        this.utenteDAO = utenteDAO;
        this.controllerValidazioneInput = controllerValidazioneInput;
    }


    @PostMapping(path = "/all/registrazione", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> aggiungiUtente(@RequestBody Utente nuovoUtente){

        if(controllerValidazioneInput.isValidRegistrazione(nuovoUtente)) {

            if (!utenteDAO.existsByNomeUtente(nuovoUtente.getNomeUtente())) {

                nuovoUtente.setActive(true);
                nuovoUtente.setMostraCome(NOMEUTENTE);
                utenteDAO.save(nuovoUtente);
                return new ResponseEntity<>("La registrazione è avvenuta con successo", HttpStatus.OK);
            }
            else{
                throw new ElementIsAlreadyPresentExcetpion("Utente già presente");
            }
        }
        else{ throw new NoValidInputException("Input non valido"); }



    }


}