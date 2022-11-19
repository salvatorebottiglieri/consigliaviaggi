package com.ingsw.consigliaviaggi.Controller;


import com.ingsw.consigliaviaggi.controllers.ControllerAggiungiRecensione;
import com.ingsw.consigliaviaggi.controllers.InterfacciaAutenticazione;
import com.ingsw.consigliaviaggi.interfaces.*;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ControllerAggiungiRecensioneTest {

    private ControllerAggiungiRecensione controllerAggiungiRecensione;
    @Mock
    private InterfacciaAutenticazione interfacciaAutenticazione;
    @Mock
    private UseCaseAggiungiRecensione useCaseAggiungiRecensione;
    @Mock
    private UseCaseValidaInputStruttura useCaseValidaInputStruttura;
    private Recensione recensione;
    private String structureId;


    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        structureId = "some id";
        recensione = new Recensione(10,"some review");
        controllerAggiungiRecensione =
                new ControllerAggiungiRecensione(interfacciaAutenticazione,
                                                useCaseValidaInputStruttura,
                                                useCaseAggiungiRecensione);
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
            public void setAuthenticated(boolean b) throws IllegalArgumentException {}
            @Override
            public String getName() {
                return "some name";
            }
        });
    }

    @Test
    void shouldControllerAggiungiRecensioneReturnOkHttpStatus(){
        //Arrange
        when(useCaseValidaInputStruttura.isValidRecensione(recensione)).thenReturn(true);
        HttpStatus expected = new ResponseEntity<Object>("some response",
                HttpStatus.OK).getStatusCode();
        //Act
        ResponseEntity<Object> response = controllerAggiungiRecensione.aggiungiRecensione(recensione,structureId);
        //Assert
        assertThat(response.getStatusCode(),is(expected));
    }

    @Test
    void shouldControllerAggiungiRecensioneReturnHttpStatusNotAcceptableWhenInputIsNotValid(){

        when(useCaseValidaInputStruttura.isValidRecensione(recensione)).thenReturn(false);
        String expected = "Input non valido";

        ResponseEntity<Object> response = controllerAggiungiRecensione.aggiungiRecensione(recensione,structureId);

        assertThat(response.getStatusCode(),is(equalTo(HttpStatus.NOT_ACCEPTABLE)));
    }
    @Test
    void shouldControllerAggiungiRecensioneReturnHttpStatusBadRequestWhenRequestIsBad(){
        when(useCaseAggiungiRecensione.aggiungiRecensione(recensione,structureId,"some name")).
                thenThrow(new NoSuchElementException());
        when(useCaseValidaInputStruttura.isValidRecensione(recensione)).thenReturn(true);


        ResponseEntity<Object> response = controllerAggiungiRecensione.aggiungiRecensione(recensione,structureId);

        assertThat(response.getStatusCode(),is(equalTo(HttpStatus.BAD_REQUEST)));

    }
}
