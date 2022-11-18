package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.exception.ElementIsAlreadyPresentExcetpion;
import com.ingsw.consigliaviaggi.interfaces.UseCaseAggiungiStruttura;
import com.ingsw.consigliaviaggi.interfaces.UseCaseValidaInputStruttura;
import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ControllerAggiungiStruttura {

    private final UseCaseAggiungiStruttura useCaseAggiungiStruttura;
    private final UseCaseValidaInputStruttura useCaseValidaInputStruttura;

    public ControllerAggiungiStruttura(UseCaseValidaInputStruttura useCaseValidaInputStruttura,
                                        UseCaseAggiungiStruttura useCaseAggiungiStruttura) {
        this.useCaseAggiungiStruttura = useCaseAggiungiStruttura;
        this.useCaseValidaInputStruttura = useCaseValidaInputStruttura;
    }

    @PostMapping(path = "/admin/aggiungistruttura", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> creaStruttura(@RequestBody Struttura nuovaStruttura) {

        if(useCaseValidaInputStruttura.isValidName(nuovaStruttura.getNome()) &&
            useCaseValidaInputStruttura.isValidAddress(nuovaStruttura.getIndirizzo()) &&
            useCaseValidaInputStruttura.isValidDescription(nuovaStruttura.getDescrizione()) &&
            useCaseValidaInputStruttura.isValidPrice(nuovaStruttura.getPrezzo())) {

            try{
                useCaseAggiungiStruttura.creaStruttura(nuovaStruttura);
                return new ResponseEntity<>("La struttura è stata aggiunta con successo", HttpStatus.OK);
            }
            catch (ElementIsAlreadyPresentExcetpion e){
                return new ResponseEntity<>("struttura già presente",HttpStatus.NOT_ACCEPTABLE);
            }
        }else{
            return new ResponseEntity<>("input non valido",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
