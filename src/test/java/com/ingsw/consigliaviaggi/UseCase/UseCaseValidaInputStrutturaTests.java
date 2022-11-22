package com.ingsw.consigliaviaggi.UseCase;

import com.ingsw.consigliaviaggi.interfaces.UseCaseValidaInputStruttura;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.useCase.UseCaseUseCaseValidaInputStrutturaImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UseCaseValidaInputStrutturaTests {

    UseCaseValidaInputStruttura useCaseUseCaseValidaInputStruttura;

    @BeforeEach
    void init(){
        useCaseUseCaseValidaInputStruttura = new UseCaseUseCaseValidaInputStrutturaImpl();
    }

    @Test
    void shouldIsValidNameReturnFalseWhenStringIsEmpty(){
        String input = "";
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidName(input);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidNameReturnFalseWhenStringLengthisGreaterThan20(){
        String input = "3mdnfi34nfm4ifn3io04nio4n3gfoi34bngopi4b323tpgoiopu3r4th94u3tiub2t4n53ubètuio4b1èio";
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidName(input);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidNameReturnTrueWhenStringIsNotEmptyAndLengthIsLessThanCap(){
        String input = "some name";
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidName(input);
        assertThat(response,is(true));
    }
    @Test
    void shouldIsValidDescriptionReturnFalseWhenStringIsEmpty(){
        String input = "";
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidDescription(input);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidDescriptionReturnFalseWhenStringLengthIsGreaterThan500(){
        String input = RandomStringUtils.randomAlphanumeric(501);
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidDescription(input);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidDescriptionReturnTruenWhenStringLengthIsLessThan500AndIsNotEmpty(){
        String input = "some description";
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidDescription(input);
        assertThat(response,is(true));
    }
    @Test
    void shouldIsValidPriceReturnFalseWhenPriceIsLessThan0(){
        int input = -1;
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidPrice(input);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidPriceReturnFalseWhenPriceIsGreaterThan5(){
        int input = 6;
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidPrice(input);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidPriceReturnTrueWhenPriceIsBetween0And5(){
        int input = 3;
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidPrice(input);
        assertThat(response,is(true));
    }
    @Test
    void shouldIsValidRecensioneReturnFalseWhenDescriptionIsEmpty(){
        Recensione recensione = new Recensione(2,"");
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidRecensione(recensione);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidRecensioneReturnFalseDescriptionLengthIsGreaterThan250(){
        String input = RandomStringUtils.randomAlphanumeric(501);
        Recensione recensione = new Recensione(2,input);
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidRecensione(recensione);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidRecensioneReturnTrueWhenDescriptionLengthIsLessThan250AndGreaterThan0(){
        String description = "some description";
        Recensione recensione = new Recensione(1,description);
        Boolean response = useCaseUseCaseValidaInputStruttura.isValidRecensione(recensione);
        assertThat(response,is(true));
    }

}
