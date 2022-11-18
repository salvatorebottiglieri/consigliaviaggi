package com.ingsw.consigliaviaggi.interfaces;

import com.ingsw.consigliaviaggi.model.DislikesUtenti;
import com.ingsw.consigliaviaggi.model.Recensione;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DislikeUtenteDAO extends CrudRepository<DislikesUtenti,String> {

    @Override
    <S extends DislikesUtenti> S save(S s);

    @Override
    void delete(DislikesUtenti dislikesUtenti);

    List<DislikesUtenti> findALLByRecensione(Recensione recensione);

}
