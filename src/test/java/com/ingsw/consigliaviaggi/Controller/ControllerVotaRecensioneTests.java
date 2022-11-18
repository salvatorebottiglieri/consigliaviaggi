package com.ingsw.consigliaviaggi.Controller;

import com.ingsw.consigliaviaggi.controllers.ControllerVotaRecensione;
import com.ingsw.consigliaviaggi.controllers.InterfacciaAutenticazione;
import com.ingsw.consigliaviaggi.interfaces.DislikeUtenteDAO;
import com.ingsw.consigliaviaggi.interfaces.LikeUtenteDAO;
import com.ingsw.consigliaviaggi.interfaces.RecensioneDAO;
import com.ingsw.consigliaviaggi.model.DislikesUtenti;
import com.ingsw.consigliaviaggi.model.LikesUtenti;
import com.ingsw.consigliaviaggi.model.Recensione;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ControllerVotaRecensioneTests {

    ControllerVotaRecensione controllerVotaRecensione;
    @Mock
    RecensioneDAO recensioneDAO;
    @Mock
    InterfacciaAutenticazione interfacciaAutenticazione;
    @Mock
    LikeUtenteDAO likeUtenteDAO;
    @Mock
    DislikeUtenteDAO dislikeUtenteDAO;
    Recensione recensione;
    List<LikesUtenti> likesUtenti;
    List<DislikesUtenti> dislikesUtenti;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        controllerVotaRecensione =
                new ControllerVotaRecensione(recensioneDAO, interfacciaAutenticazione,
                        likeUtenteDAO,dislikeUtenteDAO);
        recensione = new Recensione();
        likesUtenti = new ArrayList<>();
        dislikesUtenti = new ArrayList<>();
        when(interfacciaAutenticazione.getAuthentication()).thenReturn(new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "some name";
            }
        });
    }

    @Test
    void shouldAggiungiLikeReturnHttpStatusNotAcceptable(){
        when(recensioneDAO.findById(anyLong())).thenReturn(Optional.of(recensione));
        when(likeUtenteDAO.findALLByRecensione(recensione)).thenReturn(likesUtenti);
        when(dislikeUtenteDAO.findALLByRecensione(recensione)).thenReturn(dislikesUtenti);
        likesUtenti.add(new LikesUtenti("some name",recensione));

        ResponseEntity<Object> response =
                controllerVotaRecensione.aggiungiLike(1L);

        assertThat(response.getStatusCode(),equalTo(HttpStatus.NOT_ACCEPTABLE));
    }

    @Test
    void shouldAggiungiLikeReturnHttpStatusCodeOk(){
        when(recensioneDAO.findById(anyLong())).thenReturn(Optional.of(recensione));
        when(likeUtenteDAO.findALLByRecensione(recensione)).thenReturn(likesUtenti);
        when(dislikeUtenteDAO.findALLByRecensione(recensione)).thenReturn(dislikesUtenti);

        ResponseEntity<Object> response =
                controllerVotaRecensione.aggiungiLike(1L);

        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));

    }
    @Test
    void shouldAggiungiLikeThrowsEntityNotFoundException(){
        when(recensioneDAO.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controllerVotaRecensione.aggiungiLike(1L)
        );

        assertThat(exception.getMessage(),is("User not found"));
    }
    @Test
    void shouldAggiungiDisLikeReturnHttpStatusNotAcceptable(){
        when(recensioneDAO.findById(anyLong())).thenReturn(Optional.of(recensione));
        when(likeUtenteDAO.findALLByRecensione(recensione)).thenReturn(likesUtenti);
        when(dislikeUtenteDAO.findALLByRecensione(recensione)).thenReturn(dislikesUtenti);
        dislikesUtenti.add(new DislikesUtenti("some name",recensione));

        ResponseEntity<Object> response =
                controllerVotaRecensione.aggiungiDislike(1L);

        assertThat(response.getStatusCode(),equalTo(HttpStatus.NOT_ACCEPTABLE));
    }

    @Test
    void shouldAggiungiDisLikeReturnHttpStatusCodeOk(){
        when(recensioneDAO.findById(anyLong())).thenReturn(Optional.of(recensione));
        when(likeUtenteDAO.findALLByRecensione(recensione)).thenReturn(likesUtenti);
        when(dislikeUtenteDAO.findALLByRecensione(recensione)).thenReturn(dislikesUtenti);

        ResponseEntity<Object> response =
                controllerVotaRecensione.aggiungiDislike(1L);

        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));

    }
    @Test
    void shouldAggiungiDisLikeThrowsEntityNotFoundException(){
        when(recensioneDAO.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controllerVotaRecensione.aggiungiDislike(1L)
        );

        assertThat(exception.getMessage(),is("User not found"));
    }

}
