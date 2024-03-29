package com.ingsw.consigliaviaggi.Controller;

import com.ingsw.consigliaviaggi.controllers.ControllerModificaStruttura;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.exception.NoValidInputException;
import com.ingsw.consigliaviaggi.interfaces.UseCaseValidaInputRegistrazione;
import com.ingsw.consigliaviaggi.interfaces.UseCaseValidaInputStruttura;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ControllerModificaStrutturaTests {

    ControllerModificaStruttura controllerModificaStruttura;
    @Mock
    StrutturaDAO strutturaDAO;
    @Mock
    UseCaseValidaInputStruttura useCaseValidaInputStruttura;
    Struttura struttura;
    Indirizzo indirizzo;


    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        indirizzo = new Indirizzo("some via",0,"some city");
        struttura = new Struttura();
        controllerModificaStruttura =
                new ControllerModificaStruttura(strutturaDAO,useCaseValidaInputStruttura);
        when(useCaseValidaInputStruttura.isValidName("correct name")).thenReturn(true);
        when(useCaseValidaInputStruttura.isValidName("incorrect name")).thenReturn(false);
    }
    @Test
    void shouldModificaNomeReturnOkHttpStatus(){
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.of(struttura));

        ResponseEntity<Object> response=
                controllerModificaStruttura.modificaNome("correct name","some id");

        assertThat(response.getStatusCode(),is(HttpStatus.OK));
    }
    @Test
    void shouldModificaNomeThrowsEntityNotFoundException(){
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controllerModificaStruttura.modificaNome("correct name","some id")
        );

        assertThat(exception.getMessage(),is("User not found"));
    }
    @Test
    void shouldModificaNomeThrowsNoValidInputException(){

        NoValidInputException exception = Assertions.assertThrows(
          NoValidInputException.class,
                () -> controllerModificaStruttura.modificaNome("incorrect name", "some id")
        );

        assertThat(exception.getMessage(),is("Input non valido: nome non valido"));
    }
    @Test
    void shouldModificaIndirizzoReturnOkHttpStatusCode(){
        when(useCaseValidaInputStruttura.isValidAddress(any())).thenReturn(true);
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.of(struttura));

        ResponseEntity<Object> response =
                controllerModificaStruttura.modificaIndirizzo(indirizzo,"some id");

        assertThat(response.getStatusCode(),is(HttpStatus.OK));
    }
    @Test
    void shouldModificaIndirizzoThrowsEntityNotFoundException(){
        when(useCaseValidaInputStruttura.isValidAddress(any())).thenReturn(true);
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controllerModificaStruttura.modificaIndirizzo(indirizzo,"some id")
        );

        assertThat(exception.getMessage(),is("User not found"));
    }
    @Test
    void shouldModificaIndirizzoThrowsNoValidInputException(){
        when(useCaseValidaInputStruttura.isValidAddress(any())).thenReturn(false);

        NoValidInputException exception = Assertions.assertThrows(
                NoValidInputException.class,
                () -> controllerModificaStruttura.modificaIndirizzo(indirizzo,"some id")
        );

        assertThat(exception.getMessage(),is("Input non valido: Indirizzo non valido"));
    }

    @ParameterizedTest
    @EnumSource(value = TipoStruttura.class,mode = EnumSource.Mode.INCLUDE)
    void shouldModificaCategoriaReturnOkHttpStatusCode(TipoStruttura tipoStruttura){
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.of(struttura));

        ResponseEntity<Object> response =
                controllerModificaStruttura.modificaCategoria(tipoStruttura,"some id");

        assertThat(response.getStatusCode(),is(HttpStatus.OK));
    }

    @ParameterizedTest
    @EnumSource(value = TipoStruttura.class,mode = EnumSource.Mode.INCLUDE)
    void shouldModificaCategoriaThrowsEntityNotFoundException(TipoStruttura tipoStruttura){
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controllerModificaStruttura.modificaCategoria(tipoStruttura,"some_id")
        );

        assertThat(exception.getMessage(),is("User not found"));
    }

    @Test
    void shouldModificaPrezzoReturnOkHttpStatusCode(){
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.of(struttura));

        ResponseEntity<Object> response =
                controllerModificaStruttura.modificaPrezzo(1,"some id");

        assertThat(response.getStatusCode(),is(HttpStatus.OK));
    }
    @Test
    void shouldModificaPrezzoThrowsEntityNotFoundException(){
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controllerModificaStruttura.modificaPrezzo(1,"some id")
        );

        assertThat(exception.getMessage(),is("User not found"));
    }
    @Test
    void shouldEliminaStrutturaReturnOkHttpStatusCode(){
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.of(struttura));

        ResponseEntity<Object> response =
                controllerModificaStruttura.eliminaStruttura("some id");
        assertThat(response.getStatusCode(),is(HttpStatus.OK));
    }
    @Test
    void shouldEliminaStrutturaThrowsEntityNotFoundException(){
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controllerModificaStruttura.eliminaStruttura("some id")
        );

        assertThat(exception.getMessage(),is("User not found"));
    }
    @Test
    void shouldAggiungiFotoReturnOkHttpStatusCode(){
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.of(struttura));

        ResponseEntity<Object> response =
                controllerModificaStruttura.aggiungiFoto("some path","some id");
        assertThat(response.getStatusCode(),is(HttpStatus.OK));

    }
    @Test
    void shouldAggiungiFotoThrowsEntityNotFoundException(){
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controllerModificaStruttura.aggiungiFoto("some path","some id")
        );

        assertThat(exception.getMessage(),is("User not found"));
    }
    @Test
    void shouldModificaDescrizioneReturnOkHttpStatusCode(){
        when(useCaseValidaInputStruttura.isValidDescription(anyString())).thenReturn(true);
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.of(struttura));

        ResponseEntity<Object> response =
                controllerModificaStruttura.modificaDescrizione("some descr","some id");

        assertThat(response.getStatusCode(),is(HttpStatus.OK));
    }
    @Test
    void shouldModificaDescrizioneThrowsEntityNotFoundException(){
        when(useCaseValidaInputStruttura.isValidDescription(anyString())).thenReturn(true);
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controllerModificaStruttura.modificaDescrizione("some desc","some id")
        );

        assertThat(exception.getMessage(),is("User not found"));
    }
    @Test
    void shouldModificaDescrizioneThrowsNoValidInputException(){
        when(useCaseValidaInputStruttura.isValidDescription(anyString())).thenReturn(false);

        NoValidInputException exception = Assertions.assertThrows(
                NoValidInputException.class,
                () -> controllerModificaStruttura.modificaDescrizione("some desc","some id")
        );

        assertThat(exception.getMessage(),is("Input non valido: Descrizione non valida"));

    }


}
