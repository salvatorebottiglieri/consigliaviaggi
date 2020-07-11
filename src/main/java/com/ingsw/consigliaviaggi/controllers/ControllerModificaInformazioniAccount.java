package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.exception.NoValidInputException;
import com.ingsw.consigliaviaggi.model.Gender;
import com.ingsw.consigliaviaggi.model.Utente;
import com.ingsw.consigliaviaggi.model.VisibilitaRecensori;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@RestController
public class ControllerModificaInformazioniAccount {

    private final UtenteDAO utenteDAO;

    private final ControllerValidazioneInput controllerValidazioneInput;

    public ControllerModificaInformazioniAccount(UtenteDAO utenteDAO, ControllerValidazioneInput controllerValidazioneInput) {
        this.utenteDAO = utenteDAO;
        this.controllerValidazioneInput = controllerValidazioneInput;
    }

    @RolesAllowed("USER")
    @PutMapping("/utente/nome/{id}")
    public ResponseEntity<Object> modificaNome(@RequestBody String nome, @PathVariable String id)
    {
        if(controllerValidazioneInput.isValidNome(nome)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setNome(nome);
                utenteDAO.save(utente);
                return new ResponseEntity<>("Il nome è stato modificato con successo", HttpStatus.OK);
            }
            else{throw new EntityNotFoundException();}
        }
        throw new NoValidInputException("Input non valido: nome non valido");
    }

    @RolesAllowed("USER")
    @PutMapping("/utente/cognome/{id}")
    public ResponseEntity<Object> modificaCognome(@RequestBody String cognome, @PathVariable String id)
    {
        if(controllerValidazioneInput.isValidCognome(cognome)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setCognome(cognome);
                utenteDAO.save(utente);
                return new ResponseEntity<>("Il cognome è stato modificato con successo", HttpStatus.OK);

            }else{throw new EntityNotFoundException();}
        }
        throw new NoValidInputException("Input non valido: cognome non valido");
    }

    @RolesAllowed("USER")
    @PutMapping("/utente/indirizzoEmail/{id}")
    public ResponseEntity<Object> modificaIndirizzoEmail(@RequestBody String indirizzoEmail, @PathVariable String id)
    {
        if(controllerValidazioneInput.isValidIndirizzoEmail(indirizzoEmail)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setIndirizzoEmail(indirizzoEmail);
                utenteDAO.save(utente);
                return new ResponseEntity<>("L'indirizzo email è stato modificato con successo", HttpStatus.OK);
            }
            else{throw new EntityNotFoundException();}
        }
        throw new NoValidInputException("Input non valido: indirizzo email non valido");
    }

    @RolesAllowed("USER")
    @PutMapping("/utente/password/{id}")
    public ResponseEntity<Object> modificaPassword(@RequestBody String password, @PathVariable String id)
    {
        if(controllerValidazioneInput.isValidPassword(password)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setPassword(password);
                utenteDAO.save(utente);
                return new ResponseEntity<>("La password è stata modificata con successo", HttpStatus.OK);
            }
            else{throw new EntityNotFoundException();}
        }
        throw new NoValidInputException("Input non valido: password non valida");
    }

    @RolesAllowed("USER")
    @PutMapping("/utente/sesso/{id}")
    public boolean modificaSesso(@RequestBody Gender sesso, @PathVariable String id)
    {
        Optional<Utente> utenteOptional = utenteDAO.findById(id);

        if(utenteOptional.isPresent())
        {
            Utente utente=utenteOptional.get();
            utente.setSesso(sesso);
            utenteDAO.save(utente);
            return true;
        }
        else{throw new EntityNotFoundException();}

    }

    @RolesAllowed("USER")
    @PutMapping("/utente/city/{id}")
    public ResponseEntity<Object> modificaCity(@RequestBody String city, @PathVariable String id)
    {
        if(controllerValidazioneInput.isValidCity(city)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setCity(city);
                utenteDAO.save(utente);
                return new ResponseEntity<>("La città è stata modificata con successo", HttpStatus.OK);
            }
            else{throw new EntityNotFoundException();}
        }
        else{ throw new NoValidInputException("Input non valido: città non valida");}
    }

    @RolesAllowed("USER")
    @PutMapping("/utente/dataDiNascita/{id}")
    public ResponseEntity<Object> modificaDataDiNascita(@RequestBody Date dataDiNascita, @PathVariable String id)
    {
        if(controllerValidazioneInput.isValidDataDiNascita(dataDiNascita)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setDataDiNascita(dataDiNascita);
                utenteDAO.save(utente);
                return new ResponseEntity<>("La data di nascita è stata modificata con successo", HttpStatus.OK);
            }
            else{throw new EntityNotFoundException();}
        }
        else{ throw new NoValidInputException("Input non valido: data di nascita non valida");}
    }

    @RolesAllowed("USER")
    @PutMapping("/utente/mostraCome/{id}")
    public ResponseEntity<Object> modificaMostraCome(@RequestBody VisibilitaRecensori mostraCome, @PathVariable String id)
    {
        Optional<Utente> utenteOptional = utenteDAO.findById(id);

        if(utenteOptional.isPresent())
        {
            Utente utente=utenteOptional.get();
            utente.setMostraCome(mostraCome);
            utenteDAO.save(utente);
            return new ResponseEntity<>("La privacy è stata modificata con successo", HttpStatus.OK);
        }
        else{throw new EntityNotFoundException();}

    }

}
