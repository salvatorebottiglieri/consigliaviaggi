package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;

import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@RestController
public class ControllerAggiungiStruttura {

    private final StrutturaDAO strutturaDAO;
    private ControllerModificaStruttura controllerModificaStruttura;

    public ControllerAggiungiStruttura(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

    @RolesAllowed("ADMIN")
    @PostMapping(path = "/admin/aggiungistruttura", consumes = "application/json", produces = "application/json")
    public Struttura creaStruttura(@RequestBody Struttura nuovaStruttura) {

        if(isValidName(nuovaStruttura.getNome()) && isValidDescription(nuovaStruttura.getDescrizione()) && isValidAddress(nuovaStruttura.getIndirizzo()) && isValidPrice(nuovaStruttura.getPrezzo()) ) {

            Indirizzo indirizzo = new Indirizzo(nuovaStruttura.getIndirizzo().getVia(), nuovaStruttura.getIndirizzo().getCivico(), nuovaStruttura.getIndirizzo().getCity());
            Struttura struttura = new Struttura(nuovaStruttura.getNome(), nuovaStruttura.getDescrizione(), indirizzo, nuovaStruttura.getCategoria(), nuovaStruttura.getPrezzo(), nuovaStruttura.getFoto());

            if (!strutturaDAO.existsStrutturaByIdEquals(struttura.getId())) {
                return strutturaDAO.save(struttura);

            }
        }
       return null;

    }

    private boolean isValidName(String nome){
        int maxNome = 20;
        return nome.length() <= maxNome && !nome.isEmpty();
    }
    private boolean isValidDescription(String descrizione){
        int maxDescrizione = 100;
        return descrizione.length() <= maxDescrizione && !descrizione.isEmpty();
    }
    private boolean isValidAddress(Indirizzo indirizzo){
        String via = indirizzo.getVia();
        int civico = indirizzo.getCivico();
        String city = indirizzo.getCity();
        int maxVia = 20;
        int maxCity = 15;
        return (via.length() <= maxVia && !via.isEmpty()) && (city.length() <= maxCity && !city.isEmpty()) && (civico > 0);
    }
    private boolean isValidPrice(int prezzo){return prezzo>=0 && prezzo<=5;}



}
