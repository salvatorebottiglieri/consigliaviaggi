package com.ingsw.consigliaviaggi.Controller;

import com.ingsw.consigliaviaggi.controllers.ControllerCheckRank;
import com.ingsw.consigliaviaggi.interfaces.RecensioneDAO;
import com.ingsw.consigliaviaggi.interfaces.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.TipoRecensore;
import com.ingsw.consigliaviaggi.model.Utente;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ControllerCheckRankTests {

    private ControllerCheckRank controllerCheckRank;
    @Mock
    private UtenteDAO utenteDAO;
    @Mock
    private RecensioneDAO recensioneDAO;
    private List<Recensione> recensioneList;
    private Recensione recensione;
    private Recensione recensione1;


    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        controllerCheckRank = new ControllerCheckRank(utenteDAO,recensioneDAO);
        recensioneList = new ArrayList<>();
        recensione = new Recensione();
        recensione1 = new Recensione();
        recensioneList.add(recensione);
        recensioneList.add(recensione1);
    }

    @AfterEach
    void tearDown(){
        recensioneList =null;
        recensione = null;
        recensione1 = null;
        controllerCheckRank = null;
    }

    @Test
    void shouldCalcolaRankUtenteReturnEccelente(){
        recensione.setLikes(100);
        recensione.setDislikes(4);
        recensione1.setLikes(200);
        recensione1.setDislikes(10);
        when(recensioneDAO.findAllByAutoreEquals(any())).
                thenReturn(recensioneList);

        TipoRecensore actual = controllerCheckRank.calcolaRankUtente(new Utente());

        assertThat(actual,is(equalTo(TipoRecensore.eccellente)));
    }
    @Test
    void shouldCalcolaRankUtenteReturnOttimo(){
        recensione.setLikes(100);
        recensione.setDislikes(50);
        recensione1.setLikes(200);
        recensione1.setDislikes(100);
        when(recensioneDAO.findAllByAutoreEquals(any())).
                thenReturn(recensioneList);

        TipoRecensore actual = controllerCheckRank.calcolaRankUtente(new Utente());

        assertThat(actual,is(equalTo(TipoRecensore.ottimo)));
    }
    @Test
    void shouldCalcolaRankUtenteReturnBuono(){
        recensione.setLikes(100);
        recensione.setDislikes(50);
        recensione1.setLikes(200);
        recensione1.setDislikes(110);
        when(recensioneDAO.findAllByAutoreEquals(any())).
                thenReturn(recensioneList);

        TipoRecensore actual = controllerCheckRank.calcolaRankUtente(new Utente());

        assertThat(actual,is(equalTo(TipoRecensore.buono)));
    }
    @Test
    void shouldCalcolaRankUtenteReturnPessimo(){
        recensione.setLikes(100);
        recensione.setDislikes(450);
        recensione1.setLikes(200);
        recensione1.setDislikes(3100);
        when(recensioneDAO.findAllByAutoreEquals(any())).
                thenReturn(recensioneList);

        TipoRecensore actual = controllerCheckRank.calcolaRankUtente(new Utente());

        assertThat(actual,is(equalTo(TipoRecensore.pessimo)));
    }

}
