package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.RecensioneDAO;
import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
public class ControllerAggiungiRecensione {

    @Autowired
    private InterfacciaAutenticazione interfacciaAutenticazione;

    @Autowired
    private StrutturaDAO strutturaDAO;

    @Autowired
    private UtenteDAO utenteDAO;

    @Autowired
    private RecensioneDAO recensioneDAO;

    private ControllerValidazioneInput controllerValidazioneInput;

    @PostMapping("/user/{strutturaId}/aggiungirecensione")
    public Recensione aggiungiRecensione(@RequestBody Recensione recensione, @PathVariable String strutturaId){

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

            return recensione;

        }

        else{return null;//lancia un'eccezione
             }


    }
}
