package com.ingsw.consigliaviaggi.Controller;

import com.ingsw.consigliaviaggi.controllers.ControllerRicercaStruttura;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class ControllerRicercaStrutturaTests {

    ControllerRicercaStruttura controllerRicercaStruttura;
    @Mock
    StrutturaDAO strutturaDAO;

    Struttura struttura;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        controllerRicercaStruttura = new ControllerRicercaStruttura(strutturaDAO);
        struttura = new Struttura("some name","some descr.",
                new Indirizzo("some via",0,"some city"),
                TipoStruttura.hotel,10,"some path");
    }

    @Test
    void shouldGetStrutturaReturnStrutturaFromId(){
        Struttura expectedStructure = new Struttura();
        when(strutturaDAO.findById(anyString())).thenReturn(Optional.of(expectedStructure));
        Struttura actualStructure = controllerRicercaStruttura.getStruttura("some id");
        assertThat(actualStructure,is(expectedStructure));
    }

}
