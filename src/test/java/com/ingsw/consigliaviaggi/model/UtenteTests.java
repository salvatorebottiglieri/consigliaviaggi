package com.ingsw.consigliaviaggi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.DisplayName;


import java.util.Date;
@DisplayName("Test Utente Class")
public class UtenteTests {

    Utente utente;

    @BeforeEach
    void setUp(){utente = new Utente();}

    @Test
    void shouldConstructorProperlyInstanteUser(){
        //Arrange
        String name = "some name";
        String username = "some username";
        String surname = "some surname";
        String password ="some password";
        String email = "some email";
        Gender gender = Gender.maschio;
        String city = "some city";
        Date birthDate = new Date();

        //Act
        utente = new Utente(username,name,surname,email,password,gender,city,birthDate);

        //Assert
        assertThat(utente.getNomeUtente(),is(username));
        assertThat(utente.getNome(),is(name));
        assertThat(utente.getCognome(),is(surname));
        assertThat(utente.getCity(),is(city));
        assertThat(utente.getPassword(),is(password));
        assertThat(utente.getDataDiNascita(),is(birthDate));
        assertThat(utente.getSesso(),is(gender));
        assertThat(utente.getIndirizzoEmail(),is(email));
    }
}
