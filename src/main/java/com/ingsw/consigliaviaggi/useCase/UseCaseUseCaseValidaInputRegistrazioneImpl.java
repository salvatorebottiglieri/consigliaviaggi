package com.ingsw.consigliaviaggi.useCase;

import com.ingsw.consigliaviaggi.interfaces.UseCaseValidaInputRegistrazione;
import com.ingsw.consigliaviaggi.model.Utente;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Pattern;

@Component
public class UseCaseUseCaseValidaInputRegistrazioneImpl implements UseCaseValidaInputRegistrazione {
    @Override
    public boolean isValidName(@NotNull String name) {
        int maxNomeUtente = 20;
        return (name.length()<=maxNomeUtente && !name.isEmpty());
    }

    @Override
    public boolean isValidSurname(@NotNull String surname) {
        return isValidName(surname);
    }

    @Override
    public boolean isValidEmail(@NotNull String email) {
        String emailRegex = "^(.+)@(\\S+)";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    @Override
    public boolean isValidPassword(@NotNull String password){
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
    }


    @Override
    public boolean isValidCity(@NotNull String city) {
        return isValidName(city);
    }

    @Override
    public boolean isValidDateOfBirth(@NotNull Date date) {
        return date.before(new Date());
    }

    @Override
    public boolean isValidRegistrazione(Utente utente) {
        return  isValidPassword(utente.getPassword())&&
                isValidName(utente.getNome()) &&
                isValidCity(utente.getCity()) &&
                isValidDateOfBirth(utente.getDataDiNascita()) &&
                isValidEmail(utente.getIndirizzoEmail()) &&
                isValidSurname(utente.getCognome());

    }
}
