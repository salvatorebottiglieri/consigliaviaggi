package com.ingsw.consigliaviaggi.dao;

import com.ingsw.consigliaviaggi.model.DislikesUtenti;
import org.springframework.data.repository.CrudRepository;

public interface DislikeUtenteDAO extends CrudRepository<DislikesUtenti,String> {

    @Override
    <S extends DislikesUtenti> S save(S s);

    @Override
    void delete(DislikesUtenti dislikesUtenti);
}
