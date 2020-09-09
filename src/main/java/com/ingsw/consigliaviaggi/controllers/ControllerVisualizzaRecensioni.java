package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ControllerVisualizzaRecensioni {


    private final StrutturaDAO strutturaDAO;

    public ControllerVisualizzaRecensioni(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

    @GetMapping("/all/{strutturaId}/recenti")
    public List<Recensione> getRecensioniRecenti(@PathVariable String strutturaId){

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        Struttura struttura = strutturaOptional.get();

        List<Recensione> recensioni = struttura.getRecensioni();

        recensioni.sort(new Recensione.DataComparator());
        Collections.reverse( recensioni );

        return recensioni;


    }

    @GetMapping("/all/{strutturaId}/menorecenti")
    public List<Recensione> getRecensioniMenoRecenti(@PathVariable String strutturaId){

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        Struttura struttura = strutturaOptional.get();

        List<Recensione> recensioni = struttura.getRecensioni();

        recensioni.sort(new Recensione.DataComparator());


        return recensioni;


    }

    @GetMapping("/all/{strutturaId}/positive")
    public List<Recensione> getRecensioniPositive(@PathVariable String strutturaId){

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        Struttura struttura = strutturaOptional.get();

        List<Recensione> recensioni = struttura.getRecensioni();

        recensioni.sort(new Recensione.VoteComparator());
        Collections.reverse( recensioni );


        return recensioni;


    }

    @GetMapping("/all/{strutturaId}/negative")
    public List<Recensione> getRecensioniNegative(@PathVariable String strutturaId){

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        Struttura struttura = strutturaOptional.get();

        List<Recensione> recensioni = struttura.getRecensioni();

        recensioni.sort(new Recensione.VoteComparator());



        return recensioni;


    }
}
