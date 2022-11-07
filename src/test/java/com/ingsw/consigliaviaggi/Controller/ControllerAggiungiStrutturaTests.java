package com.ingsw.consigliaviaggi.Controller;

import com.ingsw.consigliaviaggi.controllers.ControllerAggiungiStruttura;
import com.ingsw.consigliaviaggi.controllers.ControllerValidazioneInput;
import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.exception.ElementIsAlreadyPresentExcetpion;
import com.ingsw.consigliaviaggi.exception.NoValidInputException;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ControllerAggiungiStrutturaTests {
    private ControllerAggiungiStruttura controllerAggiungiStruttura;
    @Mock
    private StrutturaDAO strutturaDAO;
    @Mock
    private ControllerValidazioneInput controllerValidazioneInput;
    private Struttura struttura;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        controllerAggiungiStruttura = new ControllerAggiungiStruttura(strutturaDAO,controllerValidazioneInput);
        struttura = new Struttura("some name"," some description",
                new Indirizzo("some via",0," some city")
                ,TipoStruttura.hotel,0,"some image");
    }

    @Test
    void shouldCreaStrutturaReturnHttpOkStatus(){
        when(controllerValidazioneInput.isValidStruttura(any())).thenReturn(true);
        when(strutturaDAO.existsStrutturaByIdEquals(anyString())).thenReturn(false);
        HttpStatus expected = new ResponseEntity<Object>("some response",
                HttpStatus.OK).getStatusCode();


        ResponseEntity<Object> response = controllerAggiungiStruttura.creaStruttura(struttura);

        assertThat(response.getStatusCode(),is(equalTo(expected)));
    }

    @Test
    void shouldCreaStrutturaThrowElementIsAlreadyPresentExcetpion(){
        when(controllerValidazioneInput.isValidStruttura(any())).thenReturn(true);
        when(strutturaDAO.existsStrutturaByIdEquals(anyString())).thenReturn(true);
        String expected = "Struttura già presente";

        ElementIsAlreadyPresentExcetpion actual = Assertions.
                assertThrows(ElementIsAlreadyPresentExcetpion.class, () ->
                        controllerAggiungiStruttura.creaStruttura(struttura));

        assertThat(actual.getMessage(),is(equalTo(expected)));
    }

    @Test
    void shouldCreaStrutturaThrowNoValidInputException(){
        when(controllerValidazioneInput.isValidStruttura(any())).thenReturn(false);
        String expected = "Input non valido";

        NoValidInputException actual = Assertions.
                assertThrows(NoValidInputException.class,() ->
                        controllerAggiungiStruttura.creaStruttura(struttura));

        assertThat(actual.getMessage(),is(equalTo(expected)));
    }
}
