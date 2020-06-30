package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ControllerModificaStruttura {

    private final StrutturaDAO strutturaDAO;

    public ControllerModificaStruttura(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

    @PutMapping("/struttura/nome/{id}")  //url che richiama questo metodo
    public boolean modificaNome(@RequestBody String nome, @PathVariable String id)
    {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);

        if(strutturaOptional.isPresent())
        {
            Struttura struttura=strutturaOptional.get();
            struttura.setNome(nome);
            strutturaDAO.save(struttura);
            return true;
        }
        return false;
    }

    @PutMapping("/struttura/descrizione/{id}")  //url che richiama questo metodo
    public boolean modificaDescrizione(@RequestBody String descrizione, @PathVariable String id)
    {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
        Struttura struttura;

        if(strutturaOptional.isPresent())
        {
            struttura=strutturaOptional.get();
            struttura.setDescrizione(descrizione);
            strutturaDAO.save(struttura);
            return true;
        }
        return false;
    }

    @PutMapping("/struttura/indirizzo/{id}")  //url che richiama questo metodo
    public boolean modificaIndirizzo(@RequestBody Indirizzo indirizzo, @PathVariable String id)
    {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
        Struttura struttura;

        if(strutturaOptional.isPresent())
        {
            struttura=strutturaOptional.get();
            struttura.setIndirizzo(indirizzo);
            strutturaDAO.save(struttura);
            return true;
        }
        return false;
    }

    @PutMapping("/struttura/categoria/{id}")  //url che richiama questo metodo
    public boolean modificaCategoria(@RequestBody TipoStruttura categoria, @PathVariable String id)
    {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
        Struttura struttura;

        if(strutturaOptional.isPresent())
        {
            struttura=strutturaOptional.get();
            struttura.setCategoria(categoria);
            strutturaDAO.save(struttura);
            return true;
        }
        return false;
    }

    @PutMapping("/struttura/prezzo/{id}")  //url che richiama questo metodo
    public boolean modificaPrezzo(@RequestBody int prezzo, @PathVariable String id)
    {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
        Struttura struttura;

        if(strutturaOptional.isPresent())
        {
            struttura=strutturaOptional.get();
            struttura.setPrezzo(prezzo);
            strutturaDAO.save(struttura);
            return true;
        }
        return false;
    }

    @DeleteMapping("/struttura/{id}")  //url che richiama questo metodo
    public boolean eliminaStruttura(@PathVariable String id)
    {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);

        if(strutturaOptional.isPresent())
        {
            strutturaDAO.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("/struttura/foto/{id}")  //url che richiama questo metodo
    public boolean aggiungiFoto(@RequestBody String foto, @PathVariable String id)
    {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
        Struttura struttura;

        if(strutturaOptional.isPresent())
        {
            struttura=strutturaOptional.get();
            struttura.setFoto(foto);
            strutturaDAO.save(struttura);
            return true;
        }
        return false;
    }

    @DeleteMapping("/struttura/foto/{id}")  //url che richiama questo metodo
    public boolean eliminaFoto(@PathVariable String id)
    {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
        Struttura struttura;

        if(strutturaOptional.isPresent())
        {
            struttura=strutturaOptional.get();
            struttura.deleteFoto();
            strutturaDAO.save(struttura);
            return true;
        }
        return false;
    }
}
