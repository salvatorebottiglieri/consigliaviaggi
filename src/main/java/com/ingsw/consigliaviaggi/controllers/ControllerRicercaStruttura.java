package com.ingsw.consigliaviaggi.controllers;


import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.interfaces.UseCaseRicercaStruttura;
import com.ingsw.consigliaviaggi.model.Filtri;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
    public List<Struttura> ricercaStruttura(@RequestBody Filtri filtri){
        return useCaseRicercaStruttura.findStruttura(filtri);
    }

}
