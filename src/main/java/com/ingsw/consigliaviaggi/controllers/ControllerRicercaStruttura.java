package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.interfaces.UseCaseRicercaStruttura;
import com.ingsw.consigliaviaggi.model.Filtri;
import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ControllerRicercaStruttura {

    private final UseCaseRicercaStruttura useCaseRicercaStruttura;

    public ControllerRicercaStruttura(UseCaseRicercaStruttura useCaseRicercaStruttura) {
        this.useCaseRicercaStruttura = useCaseRicercaStruttura;
    }

    @GetMapping("/all/{strutturaID}")
    public Struttura getStruttura(@PathVariable String strutturaID){
        return useCaseRicercaStruttura.getStruttura(strutturaID);
    }


    @PostMapping("/all/ricerca")
    public List<Struttura> ricercaStruttura(@RequestBody List<Filtri> filtri){
        return useCaseRicercaStruttura.findStrutture(filtri);
    }

}
