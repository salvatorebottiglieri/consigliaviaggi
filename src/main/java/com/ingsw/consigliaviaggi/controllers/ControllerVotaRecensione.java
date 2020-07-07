package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.DislikeUtenteDAO;
import com.ingsw.consigliaviaggi.dao.LikeUtenteDAO;
import com.ingsw.consigliaviaggi.dao.RecensioneDAO;
import com.ingsw.consigliaviaggi.model.DislikesUtenti;
import com.ingsw.consigliaviaggi.model.LikesUtenti;
import com.ingsw.consigliaviaggi.model.Recensione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class ControllerVotaRecensione {

    @Autowired
    private RecensioneDAO recensioneDAO;

    @Autowired
    private InterfacciaAutenticazione interfacciaAutenticazione;

    @Autowired
    private LikeUtenteDAO likeUtenteDAO;

    @Autowired
    private DislikeUtenteDAO dislikeUtenteDAO;


    @PutMapping("/user/{recensioneId}/addLike")
    public boolean aggiungiLike(@PathVariable Long recensioneId){

        Optional<Recensione> recensioneOptional = recensioneDAO.findById(recensioneId);
        Recensione recensione = recensioneOptional.get();


        Authentication authentication = interfacciaAutenticazione.getAuthentication();
        String nomeUtenteAutoreLike = authentication.getName();

        List<LikesUtenti> likesUtenti = likeUtenteDAO.findALLByRecensione(recensione);
        List<DislikesUtenti> dislikesUtenti = dislikeUtenteDAO.findALLByRecensione(recensione);


        for (LikesUtenti utente:likesUtenti){

            if(utente.getNomeUtente().equals(nomeUtenteAutoreLike)){return false;}

        }


        for (DislikesUtenti utente:dislikesUtenti){

            if(utente.getNomeUtente().equals(nomeUtenteAutoreLike)){

                dislikeUtenteDAO.delete(utente);
                likeUtenteDAO.save(new LikesUtenti(nomeUtenteAutoreLike,recensione));
                recensione.addLike();
                recensioneDAO.save(recensione);
                return true;

            }
        }

        likeUtenteDAO.save(new LikesUtenti(nomeUtenteAutoreLike,recensione));
        recensione.addLike();
        recensioneDAO.save(recensione);
        return true;

    }
    @PutMapping("/user/{recensioneId}/addDislike")
    public boolean aggiungiDislike(@PathVariable Long recensioneId){

        Optional<Recensione> recensioneOptional = recensioneDAO.findById(recensioneId);
        Recensione recensione = recensioneOptional.get();

        Authentication authentication = interfacciaAutenticazione.getAuthentication();
        String nomeUtenteAutoreDislike = authentication.getName();

        List<LikesUtenti> likesUtenti = likeUtenteDAO.findALLByRecensione(recensione);
        List<DislikesUtenti> dislikesUtenti = dislikeUtenteDAO.findALLByRecensione(recensione);

        for (DislikesUtenti utente:dislikesUtenti){

            if(utente.getNomeUtente().equals(nomeUtenteAutoreDislike)){return false;}

        }

        for (LikesUtenti utente:likesUtenti){

            if(utente.getNomeUtente().equals(nomeUtenteAutoreDislike)){

                likeUtenteDAO.delete(utente);
                dislikeUtenteDAO.save(new DislikesUtenti(nomeUtenteAutoreDislike,recensione));
                recensione.addDislike();
                recensioneDAO.save(recensione);
                return true;

            }
        }

        dislikeUtenteDAO.save(new DislikesUtenti(nomeUtenteAutoreDislike,recensione));
        recensione.addDislike();
        recensioneDAO.save(recensione);
        return true;

    }

}
