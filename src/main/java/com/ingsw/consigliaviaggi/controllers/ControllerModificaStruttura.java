package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.ImmagineDAO;
import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.exception.NoValidInputException;
import com.ingsw.consigliaviaggi.model.Immagine;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

@RestController
public class ControllerModificaStruttura {

    private final StrutturaDAO strutturaDAO;
    private final ImmagineDAO immagineDAO;
    private final ControllerValidazioneInput controllerValidazioneInput;

    public ControllerModificaStruttura(StrutturaDAO strutturaDAO, ImmagineDAO immagineDAO, ControllerValidazioneInput controllerValidazioneInput) {
        this.strutturaDAO = strutturaDAO;
        this.immagineDAO = immagineDAO;
        this.controllerValidazioneInput = controllerValidazioneInput;
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/admin/struttura/nome/{id}")  //url che richiama questo metodo
    public ResponseEntity<Object> modificaNome(@RequestBody String nome, @PathVariable String id) {


        if(controllerValidazioneInput.isValidNome(nome)) {
            Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);

            if (strutturaOptional.isPresent()) {
                Struttura struttura = strutturaOptional.get();
                struttura.setNome(nome);
                strutturaDAO.save(struttura);
                return new ResponseEntity<>("Il nome è stato modificato con successo", HttpStatus.OK);
            }
            else{ throw new EntityNotFoundException(); }

        }else{ throw new NoValidInputException("Input non valido: nome non valido"); }

    }

    @RolesAllowed("ADMIN")
    @PutMapping("/admin/struttura/descrizione/{id}")  //url che richiama questo metodo
    public ResponseEntity<Object> modificaDescrizione(@RequestBody String descrizione, @PathVariable String id) {


        if(controllerValidazioneInput.isValidDescriptionStruttura(descrizione)) {
            Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
            Struttura struttura;

            if (strutturaOptional.isPresent()) {
                struttura = strutturaOptional.get();
                struttura.setDescrizione(descrizione);
                strutturaDAO.save(struttura);
                return new ResponseEntity<>("La descrizione è stata modificata con successo", HttpStatus.OK);
            }
            else{ throw new EntityNotFoundException(); }
        }
        else{ throw new NoValidInputException("Input non valido: Descrizione non valida"); }



    }

    @RolesAllowed("ADMIN")
    @PutMapping("/admin/struttura/indirizzo/{id}")  //url che richiama questo metodo
    public ResponseEntity<Object> modificaIndirizzo(@RequestBody Indirizzo indirizzo, @PathVariable String id){

        if (controllerValidazioneInput.isValidAddressStruttura(indirizzo)) {

            Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
            Struttura struttura;

            if (strutturaOptional.isPresent()) {
                struttura = strutturaOptional.get();
                struttura.setIndirizzo(indirizzo);
                strutturaDAO.save(struttura);
                return new ResponseEntity<>("l'indirizzo è stato modificato con successo", HttpStatus.OK);
            }
            else{ throw new EntityNotFoundException();}

        }else{ throw new NoValidInputException("Input non valido: Indirizzo non valido"); }

    }

    @RolesAllowed("ADMIN")
    @PutMapping("/admin/struttura/categoria/{id}")  //url che richiama questo metodo
    public ResponseEntity<Object> modificaCategoria(@RequestBody TipoStruttura nuovaCategoria, @PathVariable String id)
    {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
        Struttura struttura;

        if(strutturaOptional.isPresent())
        {
            struttura=strutturaOptional.get();
            struttura.setCategoria(nuovaCategoria);
            if( struttura.freeOfCharge() ){
                struttura.setPrezzo(0);
            }
            strutturaDAO.save(struttura);
            return new ResponseEntity<>("la categoria è stata modificata con successo", HttpStatus.OK);
        }
        else{throw new EntityNotFoundException();}
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/admin/struttura/prezzo/{id}")  //url che richiama questo metodo
    public ResponseEntity<Object> modificaPrezzo(@RequestBody int prezzo, @PathVariable String id) {

            Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
            Struttura struttura;

            if (strutturaOptional.isPresent()) {
                struttura = strutturaOptional.get();
                struttura.setPrezzo(prezzo);
                strutturaDAO.save(struttura);
                return new ResponseEntity<>("Il prezzo è stato modificato con successo", HttpStatus.OK);
            }
            else{throw new EntityNotFoundException();}


    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/admin/struttura/{id}")  //url che richiama questo metodo
    public ResponseEntity<Object> eliminaStruttura(@PathVariable String id)
    {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);

        if(strutturaOptional.isPresent())
        {
            strutturaDAO.deleteById(id);
            return new ResponseEntity<>("la struttura è stata eliminata con successo", HttpStatus.OK);
        }
        else{throw new EntityNotFoundException();}

    }

    @RolesAllowed("ADMIN")
    @PutMapping("/admin/struttura/foto/{id}")  //url che richiama questo metodo
    public ResponseEntity<Object> aggiungiFoto(@RequestParam("file") MultipartFile foto, @PathVariable String id) throws IOException {

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(id);
        Struttura struttura;

        if(strutturaOptional.isPresent())
        {
            struttura=strutturaOptional.get();

            Immagine immagine = salvaImmagine(foto,struttura);


            return new ResponseEntity<>("La foto è stata aggiunta con successo", HttpStatus.OK);
        }
        else{throw new EntityNotFoundException();}

    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/admin/struttura/foto/{idFoto}")  //url che richiama questo metodo
    public ResponseEntity<Object> eliminaFoto(@PathVariable String idFoto)
    {
        Optional<Immagine> immagineOptional = immagineDAO.findById(idFoto);
        Struttura struttura;

        if(immagineOptional.isPresent())
        {
            struttura = immagineOptional.get().getStruttura();
            struttura.deleteFoto(immagineOptional.get());
            strutturaDAO.save(struttura);

            immagineDAO.delete(immagineOptional.get());

            return new ResponseEntity<>("la foto è stata eliminata con successo", HttpStatus.OK);
        }
        else{throw new EntityNotFoundException();}

    }


    private Immagine salvaImmagine(MultipartFile file, Struttura struttura) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Immagine immagine = new Immagine(fileName, file.getContentType(), file.getBytes(), struttura);

        immagineDAO.save(immagine);

        return immagine;

    }

}
