package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.exception.ElementIsAlreadyPresentExcetpion;
import com.ingsw.consigliaviaggi.exception.NoValidInputException;
import com.ingsw.consigliaviaggi.model.Immagine;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@RestController

public class ControllerAggiungiStruttura {

    private final StrutturaDAO strutturaDAO;

    private final ControllerValidazioneInput controllerValidazioneInput;

    public ControllerAggiungiStruttura(StrutturaDAO strutturaDAO, ControllerValidazioneInput controllerValidazioneInput) {
        this.strutturaDAO = strutturaDAO;
        this.controllerValidazioneInput = controllerValidazioneInput;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/admin/aggiungistruttura", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> creaStruttura(@RequestBody Struttura nuovaStruttura) {


        if(controllerValidazioneInput.isValidStruttura(nuovaStruttura) ) {

            Indirizzo indirizzo = new Indirizzo(nuovaStruttura.getIndirizzo().getVia(), nuovaStruttura.getIndirizzo().getCivico(), nuovaStruttura.getIndirizzo().getCity());
            Struttura struttura = new Struttura(nuovaStruttura.getNome(), nuovaStruttura.getDescrizione(), indirizzo, nuovaStruttura.getCategoria(), nuovaStruttura.getPrezzo(),nuovaStruttura.getFoto());

            if (!strutturaDAO.existsStrutturaByIdEquals(struttura.getId())) {

                strutturaDAO.save(struttura);
                return new ResponseEntity<>("La struttura è stata aggiunta con successo", HttpStatus.OK);

            }
            else{

                throw new ElementIsAlreadyPresentExcetpion("Struttura già presente");
            }
        }else{

            throw new NoValidInputException("Input non valido");
        }

    }





}
