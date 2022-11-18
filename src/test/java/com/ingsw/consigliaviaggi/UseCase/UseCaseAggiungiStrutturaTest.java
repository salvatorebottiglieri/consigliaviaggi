package com.ingsw.consigliaviaggi.UseCase;

import com.ingsw.consigliaviaggi.exception.ElementIsAlreadyPresentExcetpion;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.interfaces.UseCaseAggiungiStruttura;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import com.ingsw.consigliaviaggi.useCase.UseCaseAggiungiStrutturaImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UseCaseAggiungiStrutturaTest {


    private UseCaseAggiungiStruttura useCaseAggiungiStruttura;
    @Mock
    StrutturaDAO strutturaDAO;
    private Struttura struttura;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        useCaseAggiungiStruttura = new UseCaseAggiungiStrutturaImpl(strutturaDAO);
        struttura = new Struttura("some name"," some description",
                new Indirizzo("some via",0," some city")
                , TipoStruttura.hotel,0,"some image");
    }

    @Test
    void shouldCreaStrutturaReturnTrue(){
        when(strutturaDAO.existsStrutturaByIdEquals(anyString())).thenReturn(false);

        Boolean response = useCaseAggiungiStruttura.creaStruttura(struttura);

        assertThat(response,is(true));
    }
    @Test
    void shouldCreaStrutturaThrowsElementIsAlreadyPresentException(){
        when(strutturaDAO.existsStrutturaByIdEquals(anyString())).thenReturn(true);

        ElementIsAlreadyPresentExcetpion exception = Assertions.assertThrows(
                ElementIsAlreadyPresentExcetpion.class,
                () -> useCaseAggiungiStruttura.creaStruttura(struttura)
        );

        assertThat(exception.getMessage(),is("Struttura già presente"));
    }
}
