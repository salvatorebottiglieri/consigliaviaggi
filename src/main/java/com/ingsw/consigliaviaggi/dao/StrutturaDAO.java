package com.ingsw.consigliaviaggi.dao;

import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface StrutturaDAO extends CrudRepository<Struttura, String> {

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     @Override
     Struttura save(Struttura s);

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     @Override
     void delete(Struttura s);

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     List<Struttura> getByNome(String nome);

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     List<Struttura> getByPrezzo(int prezzo);

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     List<Struttura> getByIndirizzo(Indirizzo indirizzo);

   //  List<Struttura> getByGPS(String coordinate);

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     List<Struttura> getByCategoria(TipoStruttura tipoStruttura);


  // Metodi ausiliari

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     boolean existsStrutturaByIdEquals(String id);


}
