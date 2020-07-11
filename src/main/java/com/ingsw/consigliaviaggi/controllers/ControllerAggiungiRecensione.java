package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.RecensioneDAO;
import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.exception.NoValidInputException;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import javax.annotation.security.RolesAllowed;
import java.util.*;

@RestController
public class ControllerAggiungiRecensione {


    private final InterfacciaAutenticazione interfacciaAutenticazione;


    private final StrutturaDAO strutturaDAO;


    private final UtenteDAO utenteDAO;


    private final RecensioneDAO recensioneDAO;

    private final ControllerValidazioneInput controllerValidazioneInput;

    public ControllerAggiungiRecensione(InterfacciaAutenticazione interfacciaAutenticazione, StrutturaDAO strutturaDAO, UtenteDAO utenteDAO, RecensioneDAO recensioneDAO, ControllerValidazioneInput controllerValidazioneInput) {
        this.interfacciaAutenticazione = interfacciaAutenticazione;
        this.strutturaDAO = strutturaDAO;
        this.utenteDAO = utenteDAO;
        this.recensioneDAO = recensioneDAO;
        this.controllerValidazioneInput = controllerValidazioneInput;
    }

    @RolesAllowed("USER")
    @PostMapping("/user/{strutturaId}/aggiungirecensione")
    public ResponseEntity<Object> aggiungiRecensione(@RequestBody Recensione recensione, @PathVariable String strutturaId){

        if(controllerValidazioneInput.isValidRecensione(recensione)) {

            Authentication authentication = interfacciaAutenticazione.getAuthentication();

            recensione.setDataDiAggiunta(new Date());

            Optional<Utente> utenteOptional = utenteDAO.findByNomeUtente(authentication.getName());
            Utente utente = utenteOptional.get();
            recensione.setAutore(utente);

            Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
            Struttura struttura = strutturaOptional.get();
            recensione.setStruttura(struttura);

            recensioneDAO.save(recensione);

            return new ResponseEntity<>("La recensione è stata aggiunta con successo", HttpStatus.OK);

        }
        else{throw new NoValidInputException("Input non valido");}


    }
}
