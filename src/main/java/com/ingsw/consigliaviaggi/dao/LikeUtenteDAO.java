package com.ingsw.consigliaviaggi.dao;

import com.ingsw.consigliaviaggi.model.LikesUtenti;
import com.ingsw.consigliaviaggi.model.Recensione;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeUtenteDAO extends CrudRepository<LikesUtenti,String> {

    @Override
    <S extends LikesUtenti> S save(S s);

    @Override
    void delete(LikesUtenti likesUtenti);

    List<LikesUtenti> findALLByRecensione(Recensione recensione);
}
