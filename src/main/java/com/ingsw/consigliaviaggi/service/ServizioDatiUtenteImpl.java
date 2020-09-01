package com.ingsw.consigliaviaggi.service;

import com.ingsw.consigliaviaggi.dao.UtenteDAO;


import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServizioDatiUtenteImpl implements UserDetailsService {

    private final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(4);

    @Autowired
    private UtenteDAO utenteDAO;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



        if ( utenteDAO.existsByNomeUtente(username) ) {

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new SimpleGrantedAuthority("ROLE_USER") );

            Optional<Utente> utenteOptional = utenteDAO.findByNomeUtente(username);
            Utente utente = utenteOptional.get();
            UserDetails dettagliUtente = new User(utente.getNomeUtente(), bcryptEncoder.encode(utente.getPassword()),authorities);
            return dettagliUtente;
        }
        throw new UsernameNotFoundException("User not found");
    }
}