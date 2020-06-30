package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;

import org.springframework.web.bind.annotation.*;



@RestController
public class ControllerAggiungiStruttura {

    private final StrutturaDAO strutturaDAO;

    public ControllerAggiungiStruttura(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }


    @PostMapping(path = "/struttura", consumes = "application/json", produces = "application/json")
    public boolean creaStruttura(@RequestBody Struttura nuovaStruttura) {

        Indirizzo indirizzo = new Indirizzo(nuovaStruttura.getIndirizzo().getVia(),nuovaStruttura.getIndirizzo().getCivico(),nuovaStruttura.getIndirizzo().getCity());
        Struttura struttura = new Struttura(nuovaStruttura.getNome(),nuovaStruttura.getDescrizione(),indirizzo,nuovaStruttura.getCategoria(),nuovaStruttura.getPrezzo(),nuovaStruttura.getFoto());

       if(!strutturaDAO.existsStrutturaByIdEquals(struttura.getId())) {

           strutturaDAO.save(struttura);
           return true;
       }
       return false;

    }



}
