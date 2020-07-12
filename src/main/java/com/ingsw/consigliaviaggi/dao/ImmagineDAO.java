package com.ingsw.consigliaviaggi.dao;

import com.ingsw.consigliaviaggi.model.Immagine;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImmagineDAO  extends CrudRepository<Immagine,String> {


    Immagine save(Immagine s);

    Optional<Immagine> findById(String id);
}
