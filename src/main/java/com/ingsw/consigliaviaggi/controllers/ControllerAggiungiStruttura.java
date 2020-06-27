package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerAggiungiStruttura {

    private final StrutturaDAO strutturaDAO;

    public ControllerAggiungiStruttura(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

   /* @PostMapping("/struttura")
    public Struttura creaStruttura(@RequestBody Struttura nuovaStruttura){
        return strutturaDAO.save(nuovaStruttura);
    }

    @GetMapping("/struttura")
    public void annullaInserimento(){}*/

    @GetMapping("/test")
    public Struttura testController(@RequestParam(value = "nome")String name){

        return new Struttura(name);
    }
}
