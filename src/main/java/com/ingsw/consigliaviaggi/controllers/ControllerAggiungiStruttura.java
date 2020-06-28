package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ControllerAggiungiStruttura {

    private final StrutturaDAO strutturaDAO;

    public ControllerAggiungiStruttura(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

    @PostMapping(path = "/struttura", consumes = "application/json", produces = "application/json")
    public Struttura creaStruttura(@RequestBody Struttura nuovaStruttura){

        strutturaDAO.save(nuovaStruttura);

        return nuovaStruttura;
    }

    @GetMapping(path = "/struttura", consumes = "application/json", produces = "application/json")
    public void annullaInserimento(){}

    public boolean isFormAggiungiStrutturaFilled(){return true;}


}
