package com.ingsw.consigliaviaggi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@Component
public class ControllerAutenticazione implements InterfacciaAutenticazione{

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }



    @RolesAllowed("USER")
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/user/autenticazioneUtente", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> login(@RequestBody CredenzialiUtente credenzialiUtente){

       return new ResponseEntity<>(credenzialiUtente, HttpStatus.OK);

    }

    @RolesAllowed("ADMIN")
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/admin/autenticazione", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> loginAdmin(@RequestBody CredenzialiUtente credenzialiUtente){

        return new ResponseEntity<>(credenzialiUtente, HttpStatus.OK);

    }


    private static class CredenzialiUtente{
        private String username;
        private String password;

        CredenzialiUtente(String username, String password){
            this.username = username;
            this.password = password;

        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
