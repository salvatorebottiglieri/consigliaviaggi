package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.DislikeUtenteDAO;
import com.ingsw.consigliaviaggi.dao.LikeUtenteDAO;
import com.ingsw.consigliaviaggi.dao.RecensioneDAO;
import com.ingsw.consigliaviaggi.model.DislikesUtenti;
import com.ingsw.consigliaviaggi.model.LikesUtenti;
import com.ingsw.consigliaviaggi.model.Recensione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@RestController
public class ControllerVotaRecensione {


    private final RecensioneDAO recensioneDAO;


    private final InterfacciaAutenticazione interfacciaAutenticazione;


    private final LikeUtenteDAO likeUtenteDAO;


    private final DislikeUtenteDAO dislikeUtenteDAO;

    public ControllerVotaRecensione(RecensioneDAO recensioneDAO, InterfacciaAutenticazione interfacciaAutenticazione, LikeUtenteDAO likeUtenteDAO, DislikeUtenteDAO dislikeUtenteDAO) {
        this.recensioneDAO = recensioneDAO;
        this.interfacciaAutenticazione = interfacciaAutenticazione;
        this.likeUtenteDAO = likeUtenteDAO;
        this.dislikeUtenteDAO = dislikeUtenteDAO;
    }

    @RolesAllowed("USER")
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/user/{recensioneId}/addLike")
    public ResponseEntity<Object> aggiungiLike(@PathVariable Long recensioneId){

        Optional<Recensione> recensioneOptional = recensioneDAO.findById(recensioneId);

        if (recensioneOptional.isPresent()) {
            Recensione recensione = recensioneOptional.get();


            Authentication authentication = interfacciaAutenticazione.getAuthentication();
            String nomeUtenteAutoreLike = authentication.getName();

            List<LikesUtenti> likesUtenti = likeUtenteDAO.findALLByRecensione(recensione);
            List<DislikesUtenti> dislikesUtenti = dislikeUtenteDAO.findALLByRecensione(recensione);

            for (LikesUtenti utente : likesUtenti) {

                if (utente.getNomeUtente().equals(nomeUtenteAutoreLike)) {

                    return new ResponseEntity<>("Il like è stato già aggiunto", HttpStatus.NOT_ACCEPTABLE);
                }

            }


            for (DislikesUtenti utente : dislikesUtenti) {

                if (utente.getNomeUtente().equals(nomeUtenteAutoreLike)) {

                    dislikeUtenteDAO.delete(utente);
                }
            }

            likeUtenteDAO.save(new LikesUtenti(nomeUtenteAutoreLike, recensione));
            recensione.addLike();
            recensioneDAO.save(recensione);
            return new ResponseEntity<>("Il like è stato aggiunto con successo", HttpStatus.OK);

        }else{ throw new EntityNotFoundException(); }

    }

    @RolesAllowed("USER")
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/user/{recensioneId}/addDislike")
    public ResponseEntity<Object> aggiungiDislike(@PathVariable Long recensioneId){

        Optional<Recensione> recensioneOptional = recensioneDAO.findById(recensioneId);

        if(recensioneOptional.isPresent()) {


            Recensione recensione = recensioneOptional.get();

            Authentication authentication = interfacciaAutenticazione.getAuthentication();
            String nomeUtenteAutoreDislike = authentication.getName();

            List<LikesUtenti> likesUtenti = likeUtenteDAO.findALLByRecensione(recensione);
            List<DislikesUtenti> dislikesUtenti = dislikeUtenteDAO.findALLByRecensione(recensione);

            for (DislikesUtenti utente : dislikesUtenti) {

                if (utente.getNomeUtente().equals(nomeUtenteAutoreDislike)) {

                    return new ResponseEntity<>("Il dislike è stato già aggiunto", HttpStatus.NOT_ACCEPTABLE);
                }

            }

            for (LikesUtenti utente : likesUtenti) {

                if (utente.getNomeUtente().equals(nomeUtenteAutoreDislike)) {

                    likeUtenteDAO.delete(utente);

                }
            }

            dislikeUtenteDAO.save(new DislikesUtenti(nomeUtenteAutoreDislike, recensione));
            recensione.addDislike();
            recensioneDAO.save(recensione);
            return new ResponseEntity<>("Il dislike è stato aggiunto con successo", HttpStatus.OK);

        }else{throw new EntityNotFoundException(); }

    }

}
