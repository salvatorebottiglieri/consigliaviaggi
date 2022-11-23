package com.ingsw.consigliaviaggi.Controller;

import com.ingsw.consigliaviaggi.Mocks.FiltriMock;
import com.ingsw.consigliaviaggi.controllers.ControllerRicercaStruttura;
import com.ingsw.consigliaviaggi.interfaces.UseCaseRicercaStruttura;
import com.ingsw.consigliaviaggi.model.Filtri;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.contains;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class ControllerRicercaStrutturaTests {

    ControllerRicercaStruttura controllerRicercaStruttura;
    @Mock
    UseCaseRicercaStruttura useCaseRicercaStruttura;

    Struttura struttura;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        controllerRicercaStruttura = new ControllerRicercaStruttura(useCaseRicercaStruttura);
        struttura = new Struttura("some name","some descr.",
                new Indirizzo("some via",0,"some city"),
                TipoStruttura.hotel,10,"some path");
    }

    @Test
    void shouldGetStrutturaReturnStrutturaFromId(){
        Struttura expectedStructure = new Struttura();
        when(useCaseRicercaStruttura.getStruttura(anyString())).thenReturn(expectedStructure);
        Struttura actualStructure = controllerRicercaStruttura.getStruttura("some id");
        assertThat(actualStructure,is(expectedStructure));
    }
    @Test
    void shouldRicercaStrutturaReturnListOfStructure(){
        Filtri filter = new FiltriMock("some name", "some value");
        List<Filtri> filters = new ArrayList<>();
        filters.add(filter);
        Struttura struttura = new Struttura("some name"," some description",
                new Indirizzo("some via",1," some city")
                , TipoStruttura.hotel,0,"some image");
        List<Struttura> structures = new ArrayList<>();
        structures.add(struttura);
        when(useCaseRicercaStruttura.findStrutture(filters)).thenReturn(structures);

        List<Struttura> actual = controllerRicercaStruttura.ricercaStruttura(filters);
        assertThat(actual,contains(struttura));
    }

}
