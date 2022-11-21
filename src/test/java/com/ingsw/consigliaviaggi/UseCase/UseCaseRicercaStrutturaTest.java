package com.ingsw.consigliaviaggi.UseCase;

import com.ingsw.consigliaviaggi.Mocks.FiltriMock;
import com.ingsw.consigliaviaggi.factories.FiltriFactory;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.interfaces.UseCaseRicercaStruttura;

import com.ingsw.consigliaviaggi.model.*;
import com.ingsw.consigliaviaggi.useCase.UseCaseRicercaStrutturaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class UseCaseRicercaStrutturaTest {

    UseCaseRicercaStruttura useCaseRicercaStruttura;
    @Mock
    FiltriFactory filtriFactory;
    @Mock
    StrutturaDAO strutturaDao;
    Struttura struttura;
    Filtri filtri;


    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        useCaseRicercaStruttura = new UseCaseRicercaStrutturaImpl(filtriFactory,strutturaDao);
        struttura = new Struttura("some name"," some description",
                new Indirizzo("some via",1," some city")
                , TipoStruttura.hotel,0,"some image");
    }

    @Test
    void shouldGetStrutturaReturnCorrectStructure(){
        when(strutturaDao.findById(struttura.getId())).thenReturn(Optional.of(struttura));
        Struttura actual = useCaseRicercaStruttura.getStruttura(struttura.getId());
        assertThat(actual,is(struttura));
    }
    @Test
    void shouldGetStrutturaReturnNull(){
        when(strutturaDao.findById(struttura.getId())).thenReturn(Optional.empty());
        Struttura actual = useCaseRicercaStruttura.getStruttura(struttura.getId());
        assertNull(actual, "null");
    }
    @Test
    void shouldFindStrutturaReturnCorrectList(){
        List<Filtri> filters = new ArrayList<>();
        filters.add(new FiltriMock("NAME","some name"));
        when(filtriFactory.getFilters(filters)).thenReturn(filters);
        List<Struttura> actual = useCaseRicercaStruttura.findStrutture(filters);
        assertThat(actual,contains(struttura));
    }

}
