package com.ingsw.consigliaviaggi;

import com.ingsw.consigliaviaggi.controllers.ControllerValidazioneInput;
import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


public class isValidPasswordTest {


    private ControllerValidazioneInput controllerValidazioneInput;


    @BeforeEach
    public void Init(){
        controllerValidazioneInput = new ControllerValidazioneInput();
    }

    //test con strategia Black Box
    @Test
    public void testPasswordConStringaVuota(){

        String password = "";
        assertFalse(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConUnoSpazio(){

        String password = "Abc 123456@";
        assertFalse(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConNessunoSpazio(){

        String password = "Abc123456@";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConPiùDiUnoSpazio(){

        String password = "Ab c123 45 6@";
        assertFalse(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConUnNumero(){

        String password = "Abcwedfr6@";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConNessunNumero(){

        String password = "Abcjfurjsa@";
        assertFalse(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConPiùDiUnNumero(){

        String password = "Abc123456@";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConLunghezzaMinoreDiOttoCaratteri(){

        String password = "Abc1234";
        assertFalse(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConLunghezzaCompresaTraOttoEQuindiciCaratteri(){

        String password = "Abc123456@2f4";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConLunghezzaMaggioreDiQuindiciCaratteri(){

        String password = "Abc123456@2f4j86gf43gv";
        assertFalse(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConLunghezzaPariAQuindiciCaratteri(){

        String password = "Abc123456@2f4s3";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConLunghezzaPariAOttoCaratteri(){

        String password = "Abc1234@";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConNessunaLetteraMinuscola(){

        String password = "ABC123456@";
        assertFalse(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConUnaLetteraMinuscola(){

        String password = "ABc123456@24463";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConPiùDiUnaLetteraMinuscola(){

        String password = "Abc1gv3f@2f4s3";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConNessunaLetteraMaiuscola(){

        String password = "abc123456@";
        assertFalse(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConUnaLetteraMaiuscola(){

        String password = "Abc123456@24463";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConPiùDiUnaLetteraMaiuscola(){

        String password = "ABc1H0v3f@2f4S3";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConNessunCarattereSpeciale(){

        String password = "ABC12345665";
        assertFalse(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConUnCarattereSpeciale(){

        String password = "ABc123456@24463";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }

    @Test
    public void testPasswordConPiùDiUnCarattereSpeciale(){

        String password = "Abc#g]3f@2f4s3";
        assertTrue(controllerValidazioneInput.isValidPassword(password));

    }


    //test con strategia white box

    @Test
    public void testPasswordPath_111_112(){
        String password = "aaaa";
        assertFalse(controllerValidazioneInput.isValidPassword(password));
    }

    @Test
    public void testPasswordPath_111_115_116(){
        String password = "Abc123495@ ";
        assertFalse(controllerValidazioneInput.isValidPassword(password));
    }

    @Test
    public void testPasswordPath_111_115_120_123_125_127_132_133(){
        String password = "Abcfgrmg@s";
        assertFalse(controllerValidazioneInput.isValidPassword(password));
    }



    @Test
    public void testPasswordPath_111_115_120_123_125_127_128_129_132_138_149(){
        String password = "Abcfgrmgu5s";
        assertFalse(controllerValidazioneInput.isValidPassword(password));
    }

    @Test void testPasswordPath_111_115_120_123_125_127_128_129_132_138_153_156_159_161_162_167_168(){
        String password = "abf#dj23md";
        assertFalse(controllerValidazioneInput.isValidPassword(password));


    }


    @Test void testPasswordPath_111_115_120_123_125_127_128_129_132_138_153_156_159_161_162_163_164_167_173_176_179_180_182_187_188(){
        String password = "HABD@28FN3";
        assertFalse(controllerValidazioneInput.isValidPassword(password));


    }

    @Test void testPasswordPath_111_115_120_123_125_127_128_129_132_138_153_156_159_161_162_163_164_167_173_176_179_180_182_183_184_187_193() {
        String password = "Ingegneria12@";
        assertTrue(controllerValidazioneInput.isValidPassword(password));
    }



}
