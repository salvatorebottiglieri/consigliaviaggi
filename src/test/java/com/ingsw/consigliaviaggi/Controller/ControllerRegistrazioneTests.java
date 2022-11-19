package com.ingsw.consigliaviaggi.Controller;

import com.ingsw.consigliaviaggi.controllers.ControllerRegistrazione;
import com.ingsw.consigliaviaggi.interfaces.UseCaseValidaInputRegistrazione;
import com.ingsw.consigliaviaggi.interfaces.UtenteDAO;
import com.ingsw.consigliaviaggi.exception.ElementIsAlreadyPresentExcetpion;
import com.ingsw.consigliaviaggi.exception.NoValidInputException;
import com.ingsw.consigliaviaggi.model.Gender;
import com.ingsw.consigliaviaggi.model.Utente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ControllerRegistrazioneTests {

    ControllerRegistrazione controllerRegistrazione;
    @Mock
    UtenteDAO utenteDAO;
    @Mock
    UseCaseValidaInputRegistrazione useCaseValidaInputRegistrazione;
    Utente utente;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        utente = new Utente("some username","some name","some surname",
                "some address","some pass", Gender.altro,"some city",
                new Date());
        controllerRegistrazione =
                new ControllerRegistrazione(utenteDAO,useCaseValidaInputRegistrazione);

    }

    @Test
    void shouldIsValidRegistrazioneReturnHttpOkStatus(){
        when(useCaseValidaInputRegistrazione.isValidRegistrazione(utente)).thenReturn(true);
        when(utenteDAO.existsByNomeUtente(anyString())).thenReturn(false);

        ResponseEntity<Object> response = controllerRegistrazione.aggiungiUtente(utente);

        assertThat(response.getStatusCode(),is(HttpStatus.OK));
    }
    @Test
    void shouldIsValidRegistrazioneThrowsElementIsAlreadyPresentException(){
        when(useCaseValidaInputRegistrazione.isValidRegistrazione(utente)).thenReturn(true);
        when(utenteDAO.existsByNomeUtente(anyString())).thenReturn(true);

        ElementIsAlreadyPresentExcetpion exception = Assertions.assertThrows(
                ElementIsAlreadyPresentExcetpion.class,
                () -> controllerRegistrazione.aggiungiUtente(utente)
        );

        assertThat(exception.getMessage(),is("Utente già presente"));
    }
    @Test
    void shouldIsValidRegistrazioneThrowsNoValidInputException(){
        when(useCaseValidaInputRegistrazione.isValidRegistrazione(any())).thenReturn(false);

        NoValidInputException exception = Assertions.assertThrows(
                NoValidInputException.class,
                () -> controllerRegistrazione.aggiungiUtente(utente)
        );

        assertThat(exception.getMessage(),is("Input non valido"));

    }
}
