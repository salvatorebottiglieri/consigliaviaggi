package com.ingsw.consigliaviaggi.Controller;

import com.ingsw.consigliaviaggi.controllers.ControllerAggiungiStruttura;
import com.ingsw.consigliaviaggi.exception.ElementIsAlreadyPresentExcetpion;
import com.ingsw.consigliaviaggi.interfaces.UseCaseAggiungiStruttura;
import com.ingsw.consigliaviaggi.interfaces.UseCaseValidaInputStruttura;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

class ControllerAggiungiStrutturaTests {
    private ControllerAggiungiStruttura controllerAggiungiStruttura;
    @Mock
    private UseCaseAggiungiStruttura useCaseAggiungiStruttura;
    @Mock
    private UseCaseValidaInputStruttura useCaseValidaInputStruttura;
    private Struttura struttura;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        controllerAggiungiStruttura =
                new ControllerAggiungiStruttura(useCaseValidaInputStruttura,useCaseAggiungiStruttura);
        struttura = new Struttura("some name"," some description",
                new Indirizzo("some via",0," some city")
                ,TipoStruttura.hotel,0,"some image");
        when(useCaseValidaInputStruttura.isValidName(struttura.getNome())).thenReturn(true);
        when(useCaseValidaInputStruttura.isValidAddress(struttura.getIndirizzo())).thenReturn(true);
        when(useCaseValidaInputStruttura.isValidPrice(struttura.getPrezzo())).thenReturn(true);
        when(useCaseValidaInputStruttura.isValidDescription(struttura.getDescrizione())).thenReturn(true);

    }

    @Test
    void shouldCreaStrutturaReturnHttpOkStatus(){

        HttpStatus expected = new ResponseEntity<Object>("some response",
                HttpStatus.OK).getStatusCode();
        when(useCaseAggiungiStruttura.creaStruttura(struttura)).thenReturn(true);

        ResponseEntity<Object> response = controllerAggiungiStruttura.creaStruttura(struttura);

        assertThat(response.getStatusCode(),is(equalTo(expected)));
    }

    @Test
    void shouldCreaStrutturaReturnNotAcceptableHttpStatusCodeWhenInputIsNotValid(){

        when(useCaseValidaInputStruttura.isValidName(struttura.getNome())).thenReturn(false);

        ResponseEntity<Object> response = controllerAggiungiStruttura.creaStruttura(struttura);

        assertThat(response.getStatusCode(),is(HttpStatus.NOT_ACCEPTABLE));

    }

    @Test
    void shouldCreaStrutturaReturnNotAcceptableHttpStatusCodeWhenStrutturaIsAlreadyPresent(){

        when(useCaseAggiungiStruttura.creaStruttura(struttura)).
                thenThrow(new ElementIsAlreadyPresentExcetpion("Struttura già presente"));
                ResponseEntity<Object> response = controllerAggiungiStruttura.creaStruttura(struttura);

        assertThat(response.getStatusCode(),is(HttpStatus.NOT_ACCEPTABLE));
    }
}
