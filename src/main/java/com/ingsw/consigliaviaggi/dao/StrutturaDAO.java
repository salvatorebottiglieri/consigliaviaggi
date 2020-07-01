package com.ingsw.consigliaviaggi.dao;

import com.ingsw.consigliaviaggi.controllers.ControllerRicercaStruttura;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

 public interface StrutturaDAO extends CrudRepository<Struttura, String> {

      List<Struttura> findByNome(String nome);

     @Query(value = "select * from STRUTTURA,INDIRIZZO where struttura.indirizzo_id = indirizzo.id AND INDIRIZZO.city like ?1", nativeQuery = true)
      List<Struttura> findByCity(String city);

      List<Struttura> findByCategoria(TipoStruttura categoria);

     @Query(value = "select * from STRUTTURA",nativeQuery = true)
      List<Struttura> findByCoordinate(String coordinate);

      List<Struttura> findByPrezzo(int prezzo);

     //--------------------------------------------------------------------------------------------------------------------------
     @Query(value = "SELECT * FROM STRUTTURA,INDIRIZZO WHERE STRUTTURA.INDIRIZZO_ID = INDIRIZZO.ID AND INDIRIZZO.CITY LIKE ?2 AND STRUTTURA.NOME LIKE ?1",nativeQuery=true)
      List<Struttura> findByNomeAndCity(String nome, String city);

      List<Struttura> findByNomeAndCategoria(String nome, TipoStruttura categoria);

     @Query(value = "select * from STRUTTURA",nativeQuery = true)
      List<Struttura> findByNomeAndCoordinate(String nome, String coordinate);
  /*
      List<Struttura> findByNomeAndPrezzo(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city == null) && (filtri.categoria == null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByCityAndCategoria(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo == -1);
     }
      List<Struttura> findByCityAndCoordinate(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
     }
      List<Struttura> findByCityAndPrezzo(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByCategoriaAndCoordinate(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
     }
      List<Struttura> findByCategoriaAndPrezzo(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByCoordinateAndPrezzo(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city == null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
     }

     //---------------------------------------------------------------------------------------------------------------------------------

      List<Struttura> findByNomeAndCityAndCategoria(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo == -1);
     }
      List<Struttura> findByNomeAndCityAndCoordinate(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
     }
      List<Struttura> findByNomeAndCityAndPrezzo(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByCityAndCategoriaAndCoordinate(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
     }
      List<Struttura> findByCityAndCategoriaAndPrezzo(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByCategoriaAndCoordinateAndPrezzo(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByCategoriaAndNomeAndPrezzo(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByCoordinateAndPrezzoAndNome(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city == null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByCoordinateAndCityAndPrezzo(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByNomeAndCategoriaAndCoordinate(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
     }

     //---------------------------------------------------------------------------------------------------------------------------------

      List<Struttura> findByNomeAndCityAndCategoriaAndCoordinate(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
     }
      List<Struttura> findByNomeAndCityAndCategoriaAndPrezzo(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByNomeAndCityAndPrezzoAndCoordinate(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
     }
      List<Struttura> findByNomeAndPrezzoAndCoordinateAndCategoria(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
     }
      List<Struttura> findByPrezzoAndCoordinateAndCategoriaAndCity(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
     }

     //---------------------------------------------------------------------------------------------------------------------------------

      List<Struttura> findByNomeAndPrezzoAndCoordinateAndCategoriaAndCity(ControllerRicercaStruttura.Filtri filtri){

          return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
     }



*/
     // Metodi ausiliari

       boolean existsStrutturaByIdEquals(String id);


}
