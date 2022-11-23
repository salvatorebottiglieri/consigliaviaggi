package com.ingsw.consigliaviaggi.UseCase;

import com.ingsw.consigliaviaggi.interfaces.UseCaseValidaInputRegistrazione;
import com.ingsw.consigliaviaggi.model.Gender;
import com.ingsw.consigliaviaggi.model.Utente;
import com.ingsw.consigliaviaggi.useCase.UseCaseUseCaseValidaInputRegistrazioneImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCaseValidaInputRegistrazioneTest {

    UseCaseValidaInputRegistrazione useCaseValidaInputRegistrazione;

    @BeforeEach
    void init(){
        useCaseValidaInputRegistrazione = new UseCaseUseCaseValidaInputRegistrazioneImpl();
    }
    @Test
    void shouldIsValidNameReturnFalseWhenLengthIsGreaterThan20(){
        String name = RandomStringUtils.randomAlphabetic(21);
        Boolean response =useCaseValidaInputRegistrazione.isValidName(name);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidNameReturnFalseWhenStringIsEmpty(){
        String name = "";
        Boolean response =useCaseValidaInputRegistrazione.isValidName(name);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidNameReturnTrueWhenStringIsNotEmptyAndLengthIsLessThan20(){
        String name = RandomStringUtils.randomAlphabetic(20);
        Boolean response =useCaseValidaInputRegistrazione.isValidName(name);
        assertThat(response,is(true));
    }
    @Test
    void shouldIsValidSurnameReturnFalseWhenLengthIsGreaterThan20(){
        String name = RandomStringUtils.randomAlphabetic(21);
        Boolean response =useCaseValidaInputRegistrazione.isValidSurname(name);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidSurnameReturnFalseWhenStringIsEmpty(){
        String name = "";
        Boolean response =useCaseValidaInputRegistrazione.isValidSurname(name);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidSurnameReturnTrueWhenStringIsNotEmptyAndLengthIsLessThan20(){
        String name = RandomStringUtils.randomAlphabetic(20);
        Boolean response =useCaseValidaInputRegistrazione.isValidSurname(name);
        assertThat(response,is(true));
    }
    @Test
    void shouldIsValidEmailReturnFalseWhenThereIsNotAt(){
        String email = "someemail.example.com";
        Boolean response = useCaseValidaInputRegistrazione.isValidEmail(email);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidPasswordReturnTrueWhenAllConditionsAreRespected(){
        String password = "fn3i2A@32m";
        Boolean response = useCaseValidaInputRegistrazione.isValidPassword(password);
        assertThat(response,is(true));
    }
    @Test
    void shouldIsValidPasswordReturnFalseWhenLengthIsLessThan8(){
        String password = "fi2A@3";
        Boolean response = useCaseValidaInputRegistrazione.isValidPassword(password);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidPasswordReturnFalseWhenThereAreNoDigits(){
        String password = "fneirA@flm";
        Boolean response = useCaseValidaInputRegistrazione.isValidPassword(password);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidCityReturnFalseWhenLengthIsGreaterThan20(){
        String name = RandomStringUtils.randomAlphabetic(21);
        Boolean response =useCaseValidaInputRegistrazione.isValidCity(name);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidCityReturnFalseWhenStringIsEmpty(){
        String name = "";
        Boolean response =useCaseValidaInputRegistrazione.isValidCity(name);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidCityReturnTrueWhenStringIsNotEmptyAndLengthIsLessThan20(){
        String name = RandomStringUtils.randomAlphabetic(20);
        Boolean response =useCaseValidaInputRegistrazione.isValidCity(name);
        assertThat(response,is(true));
    }
    @Test
    void shouldIsValidDateOfBirthReturnFalseWhenDateIsToday(){
        Date date = new Date();
        Boolean response = useCaseValidaInputRegistrazione.isValidDateOfBirth(date);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidDateOfBirthReturnFalseWhenDateIsInTheFuture(){
        Date date = new Date(new Date().getTime()+100000);
        Boolean response = useCaseValidaInputRegistrazione.isValidDateOfBirth(date);
        assertThat(response,is(false));
    }
    @Test
    void shouldIsValidDateOfBirthReturnTrueWhenDateIsInThePast(){
        Date date = new Date(new Date().getTime()-1000);
        Boolean response = useCaseValidaInputRegistrazione.isValidDateOfBirth(date);
        assertThat(response,is(true));
    }
    @Test
    void shouldIsValidRegistrazioneReturnFalse(){
        Utente user = new Utente("some username","some name","some surname"
        ,"sasibottiglieri@gmail.com","fn3i2A@32m", Gender.altro,"some city",
                new Date());

        boolean response = useCaseValidaInputRegistrazione.isValidRegistrazione(user);
        assertThat(response,is(false));
    }
}
