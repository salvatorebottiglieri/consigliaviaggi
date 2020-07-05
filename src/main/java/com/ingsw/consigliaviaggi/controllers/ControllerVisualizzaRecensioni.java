package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.RecensioneDAO;
import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerVisualizzaRecensioni {

    @Autowired
    private RecensioneDAO recensioneDAO;

    @Autowired
    private StrutturaDAO strutturaDAO;

    @GetMapping("/all/{strutturaId}/recenti")
    public List<Recensione> getRecensioniRecenti(@PathVariable String strutturaId){

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        if(strutturaOptional.isPresent()) {
            System.out.println("ho trovato la struttura");
            List<Recensione> recensioni = recensioneDAO.findAllByStrutturaEquals(strutturaOptional.get());

        recensioni.sort(new Recensione.DataComparator());

        return recensioni;}
        return null;

    }

    @GetMapping("/all/{strutturaId}/menorecenti")
    public List<Recensione> getRecensioniMenoRecenti(@PathVariable String strutturaId){

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        List<Recensione> recensioni = recensioneDAO.findAllByStrutturaEquals(strutturaOptional.get());

        recensioni.sort(Collections.reverseOrder(new Recensione.DataComparator()));

        return recensioni;

    }

    @GetMapping("/all/{strutturaId}/positive")
    public List<Recensione> getRecensioniPositive(@PathVariable String strutturaId){

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        List<Recensione> recensioni = recensioneDAO.findAllByStrutturaEquals(strutturaOptional.get());

        recensioni.sort(new Recensione.VotoComparator());

        return recensioni;

    }

    @GetMapping("/all/{strutturaId}/negative")
    public List<Recensione> getRecensioniNegative(@PathVariable String strutturaId){

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        List<Recensione> recensioni = recensioneDAO.findAllByStrutturaEquals(strutturaOptional.get());

        recensioni.sort(Collections.reverseOrder(new Recensione.VotoComparator()));

        return recensioni;

    }
}
