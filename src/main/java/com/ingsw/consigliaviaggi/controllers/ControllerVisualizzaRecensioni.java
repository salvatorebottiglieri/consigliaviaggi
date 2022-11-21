package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.interfaces.UseCaseVisualizzaRecensioni;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RestController
public class ControllerVisualizzaRecensioni {


    private final UseCaseVisualizzaRecensioni useCaseVisualizzaRecensioni;

    public ControllerVisualizzaRecensioni(UseCaseVisualizzaRecensioni useCaseVisualizzaRecensioni) {
        this.useCaseVisualizzaRecensioni = useCaseVisualizzaRecensioni;
    }

    @GetMapping("/all/{strutturaId}/recenti")
    public List<Recensione> getRecensioniRecenti(@PathVariable String strutturaId){
        try {
            return useCaseVisualizzaRecensioni.getRecensioniRecenti(strutturaId);
        }catch (EntityNotFoundException e){
            return new ArrayList<>();
        }
    }

    @GetMapping("/all/{strutturaId}/menorecenti")
    public List<Recensione> getRecensioniMenoRecenti(@PathVariable String strutturaId){
        try {
            return useCaseVisualizzaRecensioni.getRecensioniMenoRecenti(strutturaId);
        }catch (EntityNotFoundException e){
            return new ArrayList<>();
        }
    }

    @GetMapping("/all/{strutturaId}/positive")
    public List<Recensione> getRecensioniPositive(@PathVariable String strutturaId){
        try {
            return useCaseVisualizzaRecensioni.getRecensioniPositive(strutturaId);
        }catch (EntityNotFoundException e){
            return new ArrayList<>();
        }
    }

    @GetMapping("/all/{strutturaId}/negative")
    public List<Recensione> getRecensioniNegative(@PathVariable String strutturaId){
        try {
            return useCaseVisualizzaRecensioni.getRecensioniNegative(strutturaId);
        }catch (EntityNotFoundException e){
            return new ArrayList<>();
        }
    }
}
