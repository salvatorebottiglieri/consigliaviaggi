package com.ingsw.consigliaviaggi.controllers;


import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.regex.Pattern;

@RestController
public class ControllerRegistrazione {

    private final UtenteDAO utenteDAO;

    public ControllerRegistrazione(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @PostMapping(path = "/registrazione", consumes = "application/json", produces = "application/json")
    public boolean aggiungiUtente(@RequestBody Utente nuovoUtente){

        if(isValidNome(nuovoUtente.getNome()) && isValidCognome(nuovoUtente.getCognome()) && isValidDataDiNascita(nuovoUtente.getDataDiNascita()) && isValidCity(nuovoUtente.getCity()) && isValidIndirizzoEmail(nuovoUtente.getIndirizzoEmail()) && isValidPassword(nuovoUtente.getPassword())) {

            if (!utenteDAO.existsByNomeUtente(nuovoUtente.getNomeUtente())) {

                utenteDAO.save(nuovoUtente);
                return true;
            }
        }
        return false;


    }

    public boolean isValidNomeUtente(String nomeUtente){
        int maxNomeUtente = 20;
        return (nomeUtente.length()<=maxNomeUtente && !nomeUtente.isEmpty());
    }
    public boolean isValidNome(String nome){ return isValidNomeUtente(nome);}
    public boolean isValidCognome(String cognome){return isValidNomeUtente(cognome);}
    public boolean isValidDataDiNascita(Date dataDiNascita){return dataDiNascita.before(new Date());}
    public boolean isValidCity(String city){return isValidNomeUtente(city);}
/*
Password should not contain any space.
Password should contain at least one digit(0-9).
Password length should be between 8 to 15 characters.
Password should contain at least one lowercase letter(a-z).
Password should contain at least one uppercase letter(A-Z).
Password should contain at least one special character ( @, #, %, &, !, $, etc….).
*/
    public boolean isValidPassword(String password){

        //controllo di lunghezza
        if (!((password.length() >= 8)
                && (password.length() <= 15))) {
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
                }
            }
            if (count == 0) {
                return false;
            }



            count = 0;

            // controllo la presenza di lettere maiuscole
            for (int i = 90; i <= 122; i++) {

                // type casting
                char c = (char)i;
                String str1 = Character.toString(c);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }



        return true;
    }
    public boolean isValidIndirizzoEmail(String indirizzoEmail){

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (indirizzoEmail == null)
            return false;
        return pat.matcher(indirizzoEmail).matches();

    }





}


