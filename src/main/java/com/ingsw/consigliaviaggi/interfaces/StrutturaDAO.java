package com.ingsw.consigliaviaggi.interfaces;
import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

 public interface StrutturaDAO extends CrudRepository<Struttura, String> {

      List<Struttura> findByNome(String nome);

     @Query(value = "select * from STRUTTURA,INDIRIZZO where struttura.indirizzo_id = indirizzo.id AND INDIRIZZO.city like ?1", nativeQuery = true)
      List<Struttura> findByCity(String city);

      List<Struttura> findByCategoria(String categoria);

      List<Struttura> findByPrezzo(int prezzo);

     // Metodi ausiliari
       boolean existsStrutturaByIdEquals(String id);
}
