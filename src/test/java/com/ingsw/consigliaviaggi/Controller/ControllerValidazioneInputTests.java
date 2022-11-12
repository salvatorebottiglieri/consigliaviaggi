package com.ingsw.consigliaviaggi.Controller;

import com.ingsw.consigliaviaggi.controllers.ControllerValidazioneInput;
import com.ingsw.consigliaviaggi.model.*;
import org.codehaus.plexus.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ControllerValidazioneInputTests {

    ControllerValidazioneInput controllerValidazioneInput;


    @BeforeEach
    void init(){
        controllerValidazioneInput = new ControllerValidazioneInput();
    }

    @Test
    void shouldIsValidRecensioneReturnTrueWhenDescriptionIsNotEmptyAndLessThanCap(){
        Recensione recensione = new Recensione(10,"some description");
        Boolean actualResponse = controllerValidazioneInput.isValidRecensione(recensione);
        assertThat(actualResponse,is(true));
    }
    @Test
    void shouldIsValidRecensioneReturnFalseWhenDescriptionIsEmpty(){
        Recensione recensione = new Recensione(10,"");
        Boolean actualResponse = controllerValidazioneInput.isValidRecensione(recensione);
        assertThat(actualResponse,is(false));
    }
    @Test
    void shouldIsValidRecensioneReturnFalseWhenDescriptionIsLongerThanCap(){
        String description = StringUtils.repeat("a",259);
        Recensione recensione = new Recensione(10,description);
        Boolean actualResponse = controllerValidazioneInput.isValidRecensione(recensione);
        assertThat(actualResponse,is(false));
    }
    @Test
    void shouldIsValidNameStrutturaReturnTrueWhenNameIsNotEmptyAndLessThanCap(){
        String name = "some name";
        Boolean actualResponse = controllerValidazioneInput.isValidNameStruttura(name);
        assertThat(actualResponse,is(true));
    }
    @Test
    void shouldIsValidNameStrutturaReturnFalseWhenNameIsEmpty(){
        String name = "";
        Boolean actualResponse = controllerValidazioneInput.isValidNameStruttura(name);
        assertThat(actualResponse,is(false));
    }
    @Test
    void shouldIsValidNameStrutturaReturnFalseWhenNameIsLongerThanCap(){
        String name = StringUtils.repeat("a",25);
        Boolean actualResponse = controllerValidazioneInput.isValidNameStruttura(name);
        assertThat(actualResponse,is(false));
    }
    @Test
    void shouldIsValidPasswordReturnTrue(){
        String password = "amd!i2nsJn2";
        Boolean actualResponse = controllerValidazioneInput.isValidPassword(password);
        assertThat(actualResponse,is(true));

    }
    @Test
    void shouldIsValidPasswordReturnFalseWhenThereIsAlmostOneSpace(){
        String password = "amd!i2 nsJn2";
        Boolean actualResponse = controllerValidazioneInput.isValidPassword(password);
        assertThat(actualResponse,is(false));
    }
    @Test
    void shouldIsValidPasswordReturnFalseWhenThereIsNotAlmostOneNumber(){
        String password ="amd!idnsJnr";
        Boolean actualResponse = controllerValidazioneInput.isValidPassword(password);
        assertThat(actualResponse,is(false));
    }
    @Test
    void shouldIsValidPasswordReturnFalseWhenThereIsNotAlmostOneCapitalLetter(){
        String password = "amd!i2nsjn2";
        Boolean actualResponse = controllerValidazioneInput.isValidPassword(password);
        assertThat(actualResponse,is(false));
    }
    @Test
    void shouldIsValidPasswordReturnFalseWhenThereIsNotAlmostOneJollyChar(){
        String password = "amdsi2nsJn2";
        Boolean actualResponse = controllerValidazioneInput.isValidPassword(password);
        assertThat(actualResponse,is(false));
    }
    @Test
    void shouldIsValidPasswordReturnFalseWhenLengthIsLessOrEqualThanEight(){
        String password = "amd";
        Boolean actualResponse = controllerValidazioneInput.isValidPassword(password);
        assertThat(actualResponse,is(false));
    }
    @Test
    void shouldIsValidPasswordReturnFalseWhenLengthIsGreatOrEqualThan15(){
        String password = "amd!i2nsJn23lònerkoà3npoemfp3emfwepàfjopqew";
        Boolean actualResponse = controllerValidazioneInput.isValidPassword(password);
        assertThat(actualResponse,is(false));
    }
    @Test
    void shouldIsValidStrutturaReturnTrue(){
        Struttura struttura = new Struttura("some name","some description",
                new Indirizzo("some via",1,"some city"), TipoStruttura.hotel,
                1,"some path");
        Boolean actualResponse = controllerValidazioneInput.isValidStruttura(struttura);
        assertThat(actualResponse,is(true));
    }
    @Test
    void shouldIsValidRegistrazioneReturnTrue(){
        Utente utente = new Utente("some username","some name","some surname","address@test.it",
                "amd!i2nsJn2",Gender.altro,"some city",new Date(1));
        Boolean actualResponse = controllerValidazioneInput.isValidRegistrazione(utente);
        assertThat(actualResponse,is(true));
    }
}
