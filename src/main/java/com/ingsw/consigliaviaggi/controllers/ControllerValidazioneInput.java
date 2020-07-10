package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Pattern;



@Component
public class ControllerValidazioneInput {


    private final UtenteDAO utenteDAO;

    public ControllerValidazioneInput(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }


    public boolean isValidRecensione(Recensione recensione){

        int MAXLENGTH = 250;

        String descrizione = recensione.getDescrizione();

        return descrizione.length() <= MAXLENGTH && !descrizione.isEmpty();
    }

    public boolean isValidStruttura(Struttura struttura){

        return isValidNameStruttura(struttura.getNome())
                && isValidDescriptionStruttura(struttura.getDescrizione())
                && isValidAddressStruttura(struttura.getIndirizzo())
                && isValidPriceStruttura(struttura.getPrezzo());


    }

    public boolean isValidRegistrazione(Utente utente){

        return isValidNome(utente.getNome())
                && isValidCognome(utente.getCognome())
                && isValidNomeUtente(utente.getNomeUtente())
                && isValidIndirizzoEmail(utente.getIndirizzoEmail())
                && isValidPassword(utente.getPassword())
                && isValidCity(utente.getCity())
                && isValidDataDiNascita(utente.getDataDiNascita());
    }


    private boolean isValidNameStruttura(String nome){
        int maxNome = 20;
        return nome.length() <= maxNome && !nome.isEmpty();
    }
    public boolean isValidDescriptionStruttura(String descrizione){
        int maxDescrizione = 100;
        return descrizione.length() <= maxDescrizione && !descrizione.isEmpty();
    }
    public boolean isValidAddressStruttura(Indirizzo indirizzo){

        String via = indirizzo.getVia();
        int civico = indirizzo.getCivico();
        String city = indirizzo.getCity();
        int maxVia = 50;
        int maxCity = 50;

        return via.length() <= maxVia && !(via.isEmpty()) && city.length() <= maxCity && !(city.isEmpty()) && civico > 0;

    }


    private boolean isValidPriceStruttura(int prezzo){return prezzo>=0 && prezzo<=5;}

    public boolean isValidNome(String nome){
        int maxNomeUtente = 20;
        return (nome.length()<=maxNomeUtente && !nome.isEmpty());
    }
    public boolean isValidNomeUtente(String nomeUtente){

        if(isValidNome(nomeUtente)){

            return !utenteDAO.existsByNomeUtente(nomeUtente);

        }else{

            return false;
        }

    }

    public boolean isValidCognome(String cognome){return isValidNome(cognome);}
    public boolean isValidDataDiNascita(Date dataDiNascita){return dataDiNascita.before(new Date());}
    public boolean isValidCity(String city){return isValidNome(city);}
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

        // controllo la presenza di lettere maiuscole
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
