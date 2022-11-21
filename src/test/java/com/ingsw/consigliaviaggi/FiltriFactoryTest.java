package com.ingsw.consigliaviaggi;

import com.ingsw.consigliaviaggi.factories.FiltriFactory;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Filtri;
import com.ingsw.consigliaviaggi.model.FiltroName;
import com.ingsw.consigliaviaggi.model.FiltroPrezzo;
import com.ingsw.consigliaviaggi.model.FiltroTipoStruttura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;

public class FiltriFactoryTest {

    FiltriFactory filtriFactory;
    @Mock
    StrutturaDAO strutturaDAO;
    Filtri filtroName = new FiltroName("NAME","some name");
    Filtri filtroPrice = new FiltroPrezzo("PRICE","12");
    Filtri filtroCategory = new FiltroTipoStruttura("CATEGORY","hotel");
    List<Filtri> filters;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        filtriFactory = new FiltriFactory(strutturaDAO);
        filters = new ArrayList<>();
    }
    @Test
    void shouldGetFiltersReturnOnlyNameResearcher(){
        filters.add(filtroName);
        Filtri actual =filtriFactory.getFilters(filters).get(0);
        assertThat(actual,hasProperty("name",is("NAME")));
    }
    @Test
    void shouldGetFiltersReturnOnlyPriceResearcher(){
        filters.add(filtroPrice);
        Filtri actual =filtriFactory.getFilters(filters).get(0);
        assertThat(actual,hasProperty("name",is("PRICE")));
    }
    @Test
    void shouldGetFiltersReturnOnlyCateogryResearcher(){
        filters.add(filtroCategory);
        Filtri actual =filtriFactory.getFilters(filters).get(0);
        assertThat(actual,hasProperty("name",is("CATEGORY")));
    }
}
