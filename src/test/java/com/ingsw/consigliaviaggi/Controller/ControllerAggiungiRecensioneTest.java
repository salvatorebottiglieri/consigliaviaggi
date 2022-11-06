package com.ingsw.consigliaviaggi.Controller;


import com.ingsw.consigliaviaggi.controllers.ControllerAggiungiRecensione;
import com.ingsw.consigliaviaggi.controllers.ControllerValidazioneInput;
import com.ingsw.consigliaviaggi.controllers.InterfacciaAutenticazione;
import com.ingsw.consigliaviaggi.dao.RecensioneDAO;
import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.exception.NoValidInputException;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.Utente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ControllerAggiungiRecensioneTest {

    ControllerAggiungiRecensione controllerAggiungiRecensione;

    @Mock
    InterfacciaAutenticazione interfacciaAutenticazione;
    @Mock
    StrutturaDAO strutturaDAO;
    @Mock
    UtenteDAO utenteDAO;
    @Mock
    RecensioneDAO recensioneDAO;
    @Mock
    ControllerValidazioneInput controllerValidazioneInput;
    @Mock
    Recensione recensione;
    String structureId;


    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        structureId = "some id";
        controllerAggiungiRecensione = new ControllerAggiungiRecensione(interfacciaAutenticazione,strutturaDAO,
                utenteDAO,recensioneDAO,controllerValidazioneInput);
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
                return null;
            }
        });
    }

    @Test
    void shouldControllerAggiungiRecensioneReturnOkHttpStatus(){

        //Arrange
        when(controllerValidazioneInput.isValidRecensione(any())).thenReturn(true);
        when(utenteDAO.findByNomeUtente(any())).thenReturn(Optional.of(new Utente()));
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.of(new Struttura()));
        HttpStatus expected = new ResponseEntity<Object>("some response",
                HttpStatus.OK).getStatusCode();

        //Act
        ResponseEntity<Object> response = controllerAggiungiRecensione.aggiungiRecensione(recensione,structureId);

        //Assert
        assertThat(response.getStatusCode(),is(expected));
    }

    @Test
    void shouldControllerAggiungiRecensioneThrowException(){

        when(controllerValidazioneInput.isValidRecensione(any())).thenReturn(false);
        String expected = "Input non valido";

        NoValidInputException exception = Assertions.assertThrows(NoValidInputException.class, () ->
                controllerAggiungiRecensione.aggiungiRecensione(recensione,structureId));

        assertThat(expected,is(equalTo(exception.getMessage())));
    }
}
