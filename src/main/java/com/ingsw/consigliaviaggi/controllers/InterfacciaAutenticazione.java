package com.ingsw.consigliaviaggi.controllers;

import org.springframework.security.core.Authentication;

public interface InterfacciaAutenticazione {
    Authentication getAuthentication();
}
