package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Gender;
import com.ingsw.consigliaviaggi.model.Utente;
import com.ingsw.consigliaviaggi.model.VisibilitaRecensori;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController

public class ControllerModificaInformazioniAccount {

    private final UtenteDAO utenteDAO;
    private final ControllerRegistrazione controller;

    public ControllerModificaInformazioniAccount(UtenteDAO utenteDAO, ControllerRegistrazione controller) {
        this.utenteDAO = utenteDAO;
        this.controller = controller;
    }

    @PutMapping("/utente/nome/{id}")
    public boolean modificaNome(@RequestBody String nome, @PathVariable String id)
    {
        if(controller.isValidNome(nome)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setNome(nome);
                utenteDAO.save(utente);
                return true;
            }
        }
        return false;
    }

    @PutMapping("/utente/cognome/{id}")
    public boolean modificaCognome(@RequestBody String cognome, @PathVariable String id)
    {
        if(controller.isValidCognome(cognome)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setCognome(cognome);
                utenteDAO.save(utente);
                return true;
            }
        }
        return false;
    }


    @PutMapping("/utente/indirizzoEmail/{id}")
    public boolean modificaIndirizzoEmail(@RequestBody String indirizzoEmail, @PathVariable String id)
    {
        if(controller.isValidIndirizzoEmail(indirizzoEmail)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setIndirizzoEmail(indirizzoEmail);
                utenteDAO.save(utente);
                return true;
            }
        }
        return false;
    }

    @PutMapping("/utente/password/{id}")
    public boolean modificaPassword(@RequestBody String password, @PathVariable String id)
    {
        if(controller.isValidPassword(password)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setPassword(password);
                utenteDAO.save(utente);
                return true;
            }
        }
        return false;
    }


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
        return false;
    }


    @PutMapping("/utente/city/{id}")
    public boolean modificaCity(@RequestBody String city, @PathVariable String id)
    {
        if(controller.isValidCity(city)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setCity(city);
                utenteDAO.save(utente);
                return true;
            }
        }
        return false;
    }


    @PutMapping("/utente/dataDiNascita/{id}")
    public boolean modificaDataDiNascita(@RequestBody Date dataDiNascita, @PathVariable String id)
    {
        if(controller.isValidDataDiNascita(dataDiNascita)) {
            Optional<Utente> utenteOptional = utenteDAO.findById(id);

            if (utenteOptional.isPresent()) {
                Utente utente = utenteOptional.get();
                utente.setDataDiNascita(dataDiNascita);
                utenteDAO.save(utente);
                return true;
            }
        }
        return false;
    }


    @PutMapping("/utente/mostraCome/{id}")
    public boolean modificaMostraCome(@RequestBody VisibilitaRecensori mostraCome, @PathVariable String id)
    {
        Optional<Utente> utenteOptional = utenteDAO.findById(id);

        if(utenteOptional.isPresent())
        {
            Utente utente=utenteOptional.get();
            utente.setMostraCome(mostraCome);
            utenteDAO.save(utente);
            return true;
        }
        return false;
    }

}
