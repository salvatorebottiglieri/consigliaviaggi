package com.ingsw.consigliaviaggi.interfaces;

import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecensioneDAO extends CrudRepository<Recensione,Long> {

    List<Recensione> findAllByAutoreEquals(Utente utente);

    List<Recensione> findAllByStrutturaEquals(Struttura struttura);
}
