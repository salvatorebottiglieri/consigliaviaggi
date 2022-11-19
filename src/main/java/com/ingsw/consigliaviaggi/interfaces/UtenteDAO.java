package com.ingsw.consigliaviaggi.interfaces;

import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UtenteDAO extends CrudRepository<Utente,String> {

    boolean existsByNomeUtente(String nomeUtente);
    Optional<Utente> findByNomeUtente(String nomeUtente);

}
