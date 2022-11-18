package com.ingsw.consigliaviaggi.Controller;

import com.ingsw.consigliaviaggi.controllers.ControllerModificaInformazioniAccount;
import com.ingsw.consigliaviaggi.controllers.ControllerValidazioneInput;
import com.ingsw.consigliaviaggi.interfaces.UtenteDAO;
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

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ControllerModificaInformazioniAccountTests {

    ControllerModificaInformazioniAccount controllerModificaInformazioniAccount;

    @Mock
    UtenteDAO utenteDAO;

    @Mock
    ControllerValidazioneInput controllerValidazioneInput;

    Utente user;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        controllerModificaInformazioniAccount =
                new ControllerModificaInformazioniAccount(utenteDAO,controllerValidazioneInput);
        user = new Utente("some username","some name","some surname",
                "some address","some pass", Gender.altro,"some city",
                new Date());
    }

    @Test
    void shouldGetUtenteReturnTheCorrectUser(){
        when(utenteDAO.findByNomeUtente(anyString())).
                thenReturn(Optional.of(user));

        Utente actualUser =  controllerModificaInformazioniAccount.getUtente("some username");

        assertThat(actualUser,is(user));
    }

    @Test
    void shouldGetUtenteThrowEntityNotFoundException(){
        when(utenteDAO.findByNomeUtente(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException actual = Assertions.
                assertThrows(EntityNotFoundException.class, () ->
                        controllerModificaInformazioniAccount.getUtente("some username"));

        assertThat(actual.getMessage(),is("User not found"));
    }

    @Test
    void shouldModificaNomeReturnOkHttpStatus(){
        when(controllerValidazioneInput.isValidNome(anyString())).thenReturn(true);
        when(utenteDAO.findById(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = controllerModificaInformazioniAccount.
                modificaNome(user.getNome(),"some id");

        assertThat(response.getStatusCode(),is(equalTo(HttpStatus.OK)));
    }

    @Test
    void shouldModificaNomeThrowEntityNotFoundException(){
        when(controllerValidazioneInput.isValidNome(anyString())).thenReturn(true);
        when(utenteDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () ->controllerModificaInformazioniAccount.modificaNome(user.getNome(),"some id"));

        assertThat(exception.getMessage(),is("User not found"));
    }

    @Test
    void shouldModificaNomeThrowNoValidInputException(){
        when(controllerValidazioneInput.isValidNome(anyString())).thenReturn(false);

        NoValidInputException exception = Assertions.assertThrows(
                NoValidInputException.class,
                () -> controllerModificaInformazioniAccount.modificaNome(user.getNome(),"some id"));

        assertThat(exception.getMessage(),is("Input non valido: nome non valido"));
    }
    @Test
    void shouldModificaCognomeReturnOkHttpStatus(){
        when(controllerValidazioneInput.isValidCognome(anyString())).thenReturn(true);
        when(utenteDAO.findById(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = controllerModificaInformazioniAccount.
                modificaCognome(user.getCognome(),"some id");

        assertThat(response.getStatusCode(),is(equalTo(HttpStatus.OK)));
    }

    @Test
    void shouldModificaCognomeThrowEntityNotFoundException(){
        when(controllerValidazioneInput.isValidCognome(anyString())).thenReturn(true);
        when(utenteDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () ->controllerModificaInformazioniAccount.modificaCognome(user.getCognome(),"some id"));

        assertThat(exception.getMessage(),is("User not found"));
    }

    @Test
    void shouldModificaCognomeThrowNoValidInputException(){
        when(controllerValidazioneInput.isValidCognome(anyString())).thenReturn(false);

        NoValidInputException exception = Assertions.assertThrows(
                NoValidInputException.class,
                () -> controllerModificaInformazioniAccount.modificaCognome(user.getCognome(),"some id"));

        assertThat(exception.getMessage(),is("Input non valido: cognome non valido"));
    }

    @Test
    void shouldModificaIndirizzoEmailReturnOkHttpStatus(){
        when(controllerValidazioneInput.isValidIndirizzoEmail(anyString())).thenReturn(true);
        when(utenteDAO.findById(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = controllerModificaInformazioniAccount.
                modificaIndirizzoEmail(user.getIndirizzoEmail(),"some id");

        assertThat(response.getStatusCode(),is(equalTo(HttpStatus.OK)));
    }

    @Test
    void shouldModificaIndirizzoEmailThrowEntityNotFoundException(){
        when(controllerValidazioneInput.isValidIndirizzoEmail(anyString())).thenReturn(true);
        when(utenteDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () ->controllerModificaInformazioniAccount.
                        modificaIndirizzoEmail(user.getIndirizzoEmail(),"some id"));

        assertThat(exception.getMessage(),is("User not found"));
    }

    @Test
    void shouldModificaIndirizzoEmailThrowNoValidInputException(){
        when(controllerValidazioneInput.isValidIndirizzoEmail(anyString())).thenReturn(false);

        NoValidInputException exception = Assertions.assertThrows(
                NoValidInputException.class,
                () -> controllerModificaInformazioniAccount.
                        modificaIndirizzoEmail(user.getIndirizzoEmail(),"some id"));

        assertThat(exception.getMessage(),is("Input non valido: indirizzo email non valido"));
    }
    @Test
    void shouldModificaPasswordReturnOkHttpStatus(){
        when(controllerValidazioneInput.isValidPassword(anyString())).thenReturn(true);
        when(utenteDAO.findById(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = controllerModificaInformazioniAccount.
                modificaPassword(user.getPassword(),"some id");

        assertThat(response.getStatusCode(),is(equalTo(HttpStatus.OK)));
    }

    @Test
    void shouldModificaPasswordThrowEntityNotFoundException(){
        when(controllerValidazioneInput.isValidPassword(anyString())).thenReturn(true);
        when(utenteDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () ->controllerModificaInformazioniAccount.
                        modificaPassword(user.getPassword(),"some id"));

        assertThat(exception.getMessage(),is("User not found"));
    }

    @Test
    void shouldModificaPasswordThrowNoValidInputException(){
        when(controllerValidazioneInput.isValidPassword(anyString())).thenReturn(false);

        NoValidInputException exception = Assertions.assertThrows(
                NoValidInputException.class,
                () -> controllerModificaInformazioniAccount.
                        modificaPassword(user.getPassword(),"some id"));

        assertThat(exception.getMessage(),is("Input non valido: password non valida"));
    }
    @Test
    void shouldModificaSessoReturnOkHttpStatus(){
        when(utenteDAO.findById(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = controllerModificaInformazioniAccount.
                modificaSesso(user.getSesso(),"some id");

        assertThat(response.getStatusCode(),is(equalTo(HttpStatus.OK)));
    }

    @Test
    void shouldModificaSessoThrowEntityNotFoundException(){
        when(utenteDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () ->controllerModificaInformazioniAccount.
                        modificaSesso(user.getSesso(),"some id"));

        assertThat(exception.getMessage(),is("User not found"));
    }
    @Test
    void shouldModificaDataDiNascitaReturnOkHttpStatus(){
        when(controllerValidazioneInput.isValidDataDiNascita(any())).thenReturn(true);
        when(utenteDAO.findById(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = controllerModificaInformazioniAccount.
                modificaDataDiNascita(user.getDataDiNascita(),"some id");

        assertThat(response.getStatusCode(),is(equalTo(HttpStatus.OK)));
    }

    @Test
    void shouldModificaDataDiNascitaThrowEntityNotFoundException(){
        when(controllerValidazioneInput.isValidDataDiNascita(any())).thenReturn(true);
        when(utenteDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () ->controllerModificaInformazioniAccount.
                        modificaDataDiNascita(user.getDataDiNascita(),"some id"));

        assertThat(exception.getMessage(),is("User not found"));
    }

    @Test
    void shouldModificaDataDiNascitaThrowNoValidInputException(){
        when(controllerValidazioneInput.isValidDataDiNascita(any())).thenReturn(false);

        NoValidInputException exception = Assertions.assertThrows(
                NoValidInputException.class,
                () -> controllerModificaInformazioniAccount.
                        modificaDataDiNascita(user.getDataDiNascita(),"some id"));

        assertThat(exception.getMessage(),is("Input non valido: data di nascita non valida"));
    }
    @Test
    void shouldModificaMostraComeReturnOkHttpStatus(){
        when(utenteDAO.findById(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = controllerModificaInformazioniAccount.
                modificaMostraCome(user.getMostraCome(),"some id");

        assertThat(response.getStatusCode(),is(equalTo(HttpStatus.OK)));
    }

    @Test
    void shouldModificaMostraComeThrowEntityNotFoundException(){
        when(utenteDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () ->controllerModificaInformazioniAccount.
                        modificaMostraCome(user.getMostraCome(),"some id"));

        assertThat(exception.getMessage(),is("User not found"));
    }



}
