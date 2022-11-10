package com.ingsw.consigliaviaggi;


import com.ingsw.consigliaviaggi.controllers.ControllerValidazioneInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class isValidEmailAddressTest {


    private ControllerValidazioneInput controllerValidazioneInput;


    @BeforeEach
    public void Init(){
        controllerValidazioneInput = new ControllerValidazioneInput();
    }


    @Test
    public void testEmailConStringaVuota(){

        String email= "";
        assertFalse(controllerValidazioneInput.isValidIndirizzoEmail(email));

    }

    @Test
    public void testEmailConStringaSenzaChiocciola(){

        String email= "provagmail.com";
        assertFalse(controllerValidazioneInput.isValidIndirizzoEmail(email));

    }

    @Test
    public void testEmailConStringaConUnaChiocciola(){

        String email= "prova@gmail.com";
        assertTrue(controllerValidazioneInput.isValidIndirizzoEmail(email));

    }

    @Test
    public void testEmailConPiùDiUnaChiocciola(){

        String email= "prova@@gmail.com";
        assertFalse(controllerValidazioneInput.isValidIndirizzoEmail(email));

    }

    @Test
    public void testEmailConUnPuntoDopoLaChiocciola(){

        String email= "prova@gmail.com";
        assertTrue(controllerValidazioneInput.isValidIndirizzoEmail(email));

    }

    @Test
    public void testEmailConPiùDiUnPuntoDopoLaChiocciola(){

        String email= "prova@gma.il.com";
        assertTrue(controllerValidazioneInput.isValidIndirizzoEmail(email));

    }

    @Test
    public void testEmailConNessunPuntoDopoLaChiocciola(){

        String email= "prova@gmailcom";
        assertFalse(controllerValidazioneInput.isValidIndirizzoEmail(email));

    }
    @Test
    public void testEmailConRiferimentoANull(){

        String email= null;
        assertFalse(controllerValidazioneInput.isValidIndirizzoEmail(email));

    }



    //test con strategia white box


    @Test
    public void testEmailConPath1_2_3_5_FALSE(){
        String email= "ds3i4la.cls4";
        assertFalse(controllerValidazioneInput.isValidIndirizzoEmail(email));

    }

    @Test
    public void testEmailConPath1_2_3_5_TRUE(){
        String email= "prova@tiscali.it";
        assertTrue(controllerValidazioneInput.isValidIndirizzoEmail(email));

    }







}
