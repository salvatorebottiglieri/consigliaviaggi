package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;

import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@RestController
public class ControllerAggiungiStruttura {

    private final StrutturaDAO strutturaDAO;

    private ControllerValidazioneInput controllerValidazioneInput;

    public ControllerAggiungiStruttura(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

    @RolesAllowed("ADMIN")
    @PostMapping(path = "/admin/aggiungistruttura", consumes = "application/json", produces = "application/json")
    public Struttura creaStruttura(@RequestBody Struttura nuovaStruttura) {

        if(controllerValidazioneInput.isValidStruttura(nuovaStruttura) ) {

            Indirizzo indirizzo = new Indirizzo(nuovaStruttura.getIndirizzo().getVia(), nuovaStruttura.getIndirizzo().getCivico(), nuovaStruttura.getIndirizzo().getCity());
            Struttura struttura = new Struttura(nuovaStruttura.getNome(), nuovaStruttura.getDescrizione(), indirizzo, nuovaStruttura.getCategoria(), nuovaStruttura.getPrezzo(), nuovaStruttura.getFoto());

            if (!strutturaDAO.existsStrutturaByIdEquals(struttura.getId())) {
                return strutturaDAO.save(struttura);

            }
            else{
                return null;//lancia eccezione
            }
        }else{

            return null;//lancia eccezione
        }

    }





}
