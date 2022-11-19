package com.ingsw.consigliaviaggi.UseCase;

import com.ingsw.consigliaviaggi.interfaces.RecensioneDAO;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.interfaces.UseCaseAggiungiRecensione;
import com.ingsw.consigliaviaggi.interfaces.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.Utente;
import com.ingsw.consigliaviaggi.useCase.UseCaseAggiungiRecensioneImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

public class UseCaseAggiungiRecensioneTests {

    UseCaseAggiungiRecensione useCaseAggiungiRecensione;
    @Mock
    StrutturaDAO strutturaDAO;
    @Mock
    UtenteDAO utenteDAO;
    @Mock
    RecensioneDAO recensioneDAO;
    Recensione recensione;
    String idStruttura;
    String nomeUtente;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        useCaseAggiungiRecensione = new UseCaseAggiungiRecensioneImpl(utenteDAO, strutturaDAO,recensioneDAO);
        recensione = new Recensione(10,"some description");
        nomeUtente= "some username";
        idStruttura = "some id";
    }
    @Test
    void shouldAggiungiRecensioneReturnTrueWhenReviewIsAdded(){
        when(utenteDAO.findByNomeUtente(nomeUtente)).thenReturn(Optional.of(new Utente()));
        when(strutturaDAO.findById(idStruttura)).thenReturn(Optional.of(new Struttura()));

        Boolean response = useCaseAggiungiRecensione.aggiungiRecensione(recensione,idStruttura,nomeUtente);

        assertThat(response,is(true));
    }
    @Test
    void shouldAggiungiRecensioneThrowsExceptionWhenUserOStructureAreNotFound(){
        when(utenteDAO.findByNomeUtente(nomeUtente)).thenReturn(Optional.of(new Utente()));
        when(strutturaDAO.findById(idStruttura)).thenReturn(Optional.empty());

        NoSuchElementException exception = Assertions.assertThrows(
                NoSuchElementException.class,
                () -> useCaseAggiungiRecensione.aggiungiRecensione(recensione,idStruttura,nomeUtente)
        );

        assertThat(exception.getMessage(),is("struttura o utente non presenti nel sistema"));
    }
}
