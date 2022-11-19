package com.ingsw.consigliaviaggi.interfaces;

import com.ingsw.consigliaviaggi.model.Utente;

import java.util.Date;

public interface UseCaseValidaInputRegistrazione {

    boolean isValidName(String name);
    boolean isValidSurname(String surname);
    boolean isValidEmail(String email);
    boolean isValidPassword(String password);
    boolean isValidCity(String city);
    boolean isValidDateOfBirth(Date date);
    boolean isValidRegistrazione(Utente utente);
}
