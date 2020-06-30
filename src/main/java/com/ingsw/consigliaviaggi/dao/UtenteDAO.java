package com.ingsw.consigliaviaggi.dao;

import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDAO extends CrudRepository<Utente,String> {
<<<<<<< HEAD
    boolean existsByNomeUtente(String nomeUtente);
=======

   boolean existsByNomeUtente(String nomeUtente);

>>>>>>> master
}
