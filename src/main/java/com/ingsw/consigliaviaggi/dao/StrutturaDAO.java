package com.ingsw.consigliaviaggi.dao;

import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.hibernate.tool.schema.extract.internal.IndexInformationImpl;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;


public interface StrutturaDAO extends CrudRepository<Struttura, Long> {

     List<Struttura> getByNome(String nome);

     List<Struttura> getByPrezzo(int prezzo);

     List<Struttura> getByIndirizzo(Indirizzo indirizzo);

   //  List<Struttura> getByGPS(String coordinate);

     List<Struttura> getByCategoria(TipoStruttura tipoStruttura);

  // Metodi ausiliari

     Struttura findByNomeAndIndirizzo(String nome, Indirizzo indirizzo);

     boolean existsStrutturaByIdEquals(String id);
}
