package com.ingsw.consigliaviaggi.service;

import com.ingsw.consigliaviaggi.dao.UtenteDAO;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServizioDatiUtenteImpl implements UserDetailsService {

    @Autowired
    private UtenteDAO utenteDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Utente> user = utenteDAO.findByNomeUtente(userName);

        user.orElseThrow(() -> new UsernameNotFoundException(userName + " non trovato."));

        return user.map(DatiUtenteImpl::new).get();
    }

}
