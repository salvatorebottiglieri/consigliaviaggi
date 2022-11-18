package com.ingsw.consigliaviaggi.useCase;

import com.ingsw.consigliaviaggi.interfaces.UseCaseValidaInputRegistrazione;

import java.util.Date;
import java.util.regex.Pattern;

public class UseCaseUseCaseValidaInputRegistrazioneImpl implements UseCaseValidaInputRegistrazione {
    @Override
    public boolean isValidName(String name) {
        int maxNomeUtente = 20;
        return (name.length()<=maxNomeUtente && !name.isEmpty());
    }

    @Override
    public boolean isValidSurname(String surname) {
        return isValidName(surname);
    }

    @Override
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    @Override
    public boolean isValidPassword(String password) {

        //controllo di lunghezza
        if (!((password.length() >= 8) && (password.length() <= 15))) {
            return false;
        }
        //controllo di eventuali elementi vuoti
        if (password.contains(" ")) {
            return false;
        }
        //controllo presenza di numeri

        int count = 0;


        for (int i = 0; i <= 9; i++) {

            String str1 = Integer.toString(i);

            if (password.contains(str1)) {
                count = 1;
                break;
            }
        }
        if (count == 0) {
            return false;
        }


        // controllo la presenza di caratteri speciali
        if (!(password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|"))) {
            return false;
        }


        count = 0;

        // controllo la presenza di lettere maiuscole
        for (int i = 65; i <= 90; i++) {

            // type casting
            char c = (char)i;

            String str1 = Character.toString(c);
            if (password.contains(str1)) {
                count = 1;
                break;
            }
        }
        if (count == 0) {
            return false;
        }



        count = 0;

        // controllo la presenza di lettere minuscole
        for (int i = 90; i <= 122; i++) {

            // type casting
            char c = (char)i;
            String str1 = Character.toString(c);

            if (password.contains(str1)) {
                count = 1;
                break;
            }
        }
        if (count == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidCity(String city) {
        return isValidName(city);
    }

    @Override
    public boolean isValidDateOfBirth(Date date) {
        return date.before(new Date());
    }
}
