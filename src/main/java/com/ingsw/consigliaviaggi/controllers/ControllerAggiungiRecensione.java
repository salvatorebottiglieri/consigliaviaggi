package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.interfaces.*;
import com.ingsw.consigliaviaggi.model.Recensione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.security.RolesAllowed;
import java.util.*;

@RestController
public class ControllerAggiungiRecensione {

    private final InterfacciaAutenticazione interfacciaAutenticazione;
    private final UseCaseValidaInputStruttura useCaseValidaInputStruttura;
    private final UseCaseAggiungiRecensione useCaseAggiungiRecensione;

    public ControllerAggiungiRecensione(InterfacciaAutenticazione interfacciaAutenticazione,
                                        UseCaseValidaInputStruttura useCaseValidaInputStruttura,
                                        UseCaseAggiungiRecensione useCaseAggiungiRecensione) {
        this.interfacciaAutenticazione = interfacciaAutenticazione;
        this.useCaseValidaInputStruttura = useCaseValidaInputStruttura;
        this.useCaseAggiungiRecensione = useCaseAggiungiRecensione;
    }

    @RolesAllowed("USER")
    @PostMapping("/user/{strutturaId}/aggiungirecensione")
    public ResponseEntity<Object> aggiungiRecensione(@RequestBody Recensione recensione,
                                                     @PathVariable String strutturaId){

        if(useCaseValidaInputStruttura.isValidRecensione(recensione)) {
            String nomeUtente = interfacciaAutenticazione.getAuthentication().getName();
            try {
                useCaseAggiungiRecensione.aggiungiRecensione(recensione, strutturaId, nomeUtente);
                return new ResponseEntity<>("La recensione è stata aggiunta con successo", HttpStatus.OK);
            }
            catch (NoSuchElementException exception){
                return new ResponseEntity<>("Impossibile aggiungere la recensione", HttpStatus.BAD_REQUEST);
            }
        }
        else{ return new ResponseEntity<>("Input non valido",HttpStatus.NOT_ACCEPTABLE);}
    }
}
