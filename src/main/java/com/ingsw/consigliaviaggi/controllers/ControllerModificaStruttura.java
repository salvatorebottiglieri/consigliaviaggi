package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
public class ControllerModificaStruttura {

    private final StrutturaDAO strutturaDAO;

    @Autowired
    private ControllerValidazioneInput controllerValidazioneInput;

    public ControllerModificaStruttura(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/struttura/nome/{id}")  //url che richiama questo metodo
    public boolean modificaNome(@RequestBody String nome, @PathVariable String id) {


        if(controllerValidazioneInput.isValidNome(nome)) {
            Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);

            if (strutturaOptional.isPresent()) {
                Struttura struttura = strutturaOptional.get();
                struttura.setNome(nome);
                strutturaDAO.save(struttura);
                return true;
            }
        }else{

            return false;//lancia un'eccezione
        }
        return false;
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/struttura/descrizione/{id}")  //url che richiama questo metodo
    public boolean modificaDescrizione(@RequestBody String descrizione, @PathVariable String id) {


        if(controllerValidazioneInput.isValidDescriptionStruttura(descrizione)) {
            Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
            Struttura struttura;

            if (strutturaOptional.isPresent()) {
                struttura = strutturaOptional.get();
                struttura.setDescrizione(descrizione);
                strutturaDAO.save(struttura);
                return true;
            }
        }
        return false;//lancia eccezzione
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/struttura/indirizzo/{id}")  //url che richiama questo metodo
    public boolean modificaIndirizzo(@RequestBody Indirizzo indirizzo, @PathVariable String id){

        if (controllerValidazioneInput.isValidAddressStruttura(indirizzo)) {

            Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
            Struttura struttura;

            if (strutturaOptional.isPresent()) {
                struttura = strutturaOptional.get();
                struttura.setIndirizzo(indirizzo);
                strutturaDAO.save(struttura);
                return true;
            }
        }
        return false;//lancia eccezione
    }

    @RolesAllowed("ADMIN")
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

    @RolesAllowed("ADMIN")
    @PutMapping("/struttura/prezzo/{id}")  //url che richiama questo metodo
    public boolean modificaPrezzo(@RequestBody int prezzo, @PathVariable String id) {

            Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
            Struttura struttura;

            if (strutturaOptional.isPresent()) {
                struttura = strutturaOptional.get();
                struttura.setPrezzo(prezzo);
                strutturaDAO.save(struttura);
                return true;
            }

        return false;//lancia eccezione
    }

    @RolesAllowed("ADMIN")
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

    @RolesAllowed("ADMIN")
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

    @RolesAllowed("ADMIN")
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
