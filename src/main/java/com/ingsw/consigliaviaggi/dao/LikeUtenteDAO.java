package com.ingsw.consigliaviaggi.dao;

import com.ingsw.consigliaviaggi.model.LikesUtenti;
import org.springframework.data.repository.CrudRepository;

public interface LikeUtenteDAO extends CrudRepository<LikesUtenti,String> {

    @Override
    <S extends LikesUtenti> S save(S s);

    @Override
    void delete(LikesUtenti likesUtenti);
}
