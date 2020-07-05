package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import com.sun.rowset.FilteredRowSetImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class ControllerRicercaStruttura {

    private final StrutturaDAO strutturaDAO;

    public ControllerRicercaStruttura(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

    @PostMapping("/all/ricerca")
    public List<Struttura> ricercaStruttura(@RequestBody Filtri filtri){

       if(filtri.searchOnlyByNome(filtri)){ return strutturaDAO.findByNome(filtri.getNome());}

       if(filtri.searchOnlyByCategoria(filtri)){return strutturaDAO.findByCategoria(filtri.getCategoria());}

       if(filtri.searchOnlyByCity(filtri)){return strutturaDAO.findByCity(filtri.getCity());}

       if(filtri.searchOnlyByCoordinate(filtri)){return strutturaDAO.findByCoordinate(filtri.getCoordinate()); }

       if(filtri.searchOnlyByPrezzo(filtri)){return strutturaDAO.findByPrezzo(filtri.getPrezzo());}



       if(filtri.searchOnlyByNomeAndCity(filtri)){return strutturaDAO.findByNomeAndCity(filtri.getNome(),filtri.getCity());}

       if(filtri.searchOnlyByNomeAndCategoria(filtri)){return strutturaDAO.findByNomeAndCategoria(filtri.getNome(),filtri.getCategoria());}

       if(filtri.searchOnlyByNomeAndCoordinate(filtri)){return strutturaDAO.findByNomeAndCoordinate(filtri.getNome(),filtri.getCoordinate());}

       if(filtri.searchOnlyByNomeAndPrezzo(filtri)){return strutturaDAO.findByNomeAndPrezzo(filtri.getNome(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCityAndCategoria(filtri)){return strutturaDAO.findByCityAndCategoria(filtri.getCity(),filtri.getCategoria().toString());}

       if(filtri.searchOnlyByCityAndCoordinate(filtri)){return strutturaDAO.findByCityAndCoordinate(filtri.getCity(),filtri.getCoordinate()); }

       if(filtri.searchOnlyByCityAndPrezzo(filtri)){return strutturaDAO.findByCityAndPrezzo(filtri.getCity(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCategoriaAndCoordinate(filtri)){return strutturaDAO.findByCategoriaAndCoordinate(filtri.getCategoria().toString(),filtri.getCoordinate());}

       if(filtri.searchOnlyByCategoriaAndPrezzo(filtri)){return strutturaDAO.findByCategoriaAndPrezzo(filtri.getCategoria(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCoordinateAndPrezzo(filtri)){return strutturaDAO.findByCoordinateAndPrezzo(filtri.getCoordinate(),filtri.getPrezzo());}




       if(filtri.searchOnlyByNomeAndCityAndCategoria(filtri)){return strutturaDAO.findByNomeAndCityAndCategoria(filtri.getNome(),filtri.getCity(),filtri.getCategoria().toString());}

       if(filtri.searchOnlyByNomeAndCityAndCoordinate(filtri)){return strutturaDAO.findByNomeAndCityAndCoordinate(filtri.getNome(),filtri.getCity(),filtri.getCoordinate());}

       if(filtri.searchOnlyByNomeAndCityAndPrezzo(filtri)){return strutturaDAO.findByNomeAndCityAndPrezzo(filtri.getNome(),filtri.getCity(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCityAndCategoriaAndCoordinate(filtri)){return strutturaDAO.findByCityAndCategoriaAndCoordinate(filtri.getCity(),filtri.getCategoria().toString(),filtri.getCoordinate());}

       if(filtri.searchOnlyByCityAndCategoriaAndPrezzo(filtri)){return strutturaDAO.findByCityAndCategoriaAndPrezzo(filtri.getCity(),filtri.getCategoria().toString(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCategoriaAndCoordinateAndPrezzo(filtri)){return strutturaDAO.findByCategoriaAndCoordinateAndPrezzo(filtri.getCategoria().toString(),filtri.getCoordinate(),filtri.getPrezzo()); }

       if(filtri.searchOnlyByCategoriaAndNomeAndPrezzo(filtri)){return strutturaDAO.findByCategoriaAndNomeAndPrezzo(filtri.getCategoria(),filtri.getNome(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCoordinateAndPrezzoAndNome(filtri)){return strutturaDAO.findByCoordinateAndPrezzoAndNome(filtri.getCoordinate(),filtri.getPrezzo(),filtri.getNome());}

       if(filtri.searchOnlyByCoordinateAndCityAndPrezzo(filtri)){return strutturaDAO.findByCoordinateAndCityAndPrezzo(filtri.getCoordinate(),filtri.getCity(),filtri.getPrezzo());}

       if(filtri.searchOnlyByNomeAndCategoriaAndCoordinate(filtri)){return strutturaDAO.findByNomeAndCategoriaAndCoordinate(filtri.getNome(),filtri.getCategoria().toString(),filtri.getCoordinate());}



       if(filtri.searchOnlyByNomeAndCityAndCategoriaAndCoordinate(filtri)){return strutturaDAO.findByNomeAndCityAndCategoriaAndCoordinate(filtri.getNome(),filtri.getCity(),filtri.getCategoria().toString(),filtri.getCoordinate());}

       if(filtri.searchOnlyByNomeAndCityAndCategoriaAndPrezzo(filtri)){return strutturaDAO.findByNomeAndCityAndCategoriaAndPrezzo(filtri.getNome(),filtri.getCity(),filtri.getCategoria().toString(),filtri.getPrezzo());}

       if(filtri.searchOnlyByNomeAndCityAndPrezzoAndCoordinate(filtri)){return strutturaDAO.findByNomeAndCityAndPrezzoAndCoordinate(filtri.getNome(),filtri.getCity(),filtri.getPrezzo(),filtri.getCoordinate());}

       if(filtri.searchOnlyByNomeAndPrezzoAndCoordinateAndCategoria(filtri)){return strutturaDAO.findByNomeAndPrezzoAndCoordinateAndCategoria(filtri.getNome(),filtri.getPrezzo(),filtri.getCoordinate(),filtri.getCategoria().toString());}

       if(filtri.searchOnlyByPrezzoAndCoordinateAndCategoriaAndCity(filtri)){return strutturaDAO.findByPrezzoAndCoordinateAndCategoriaAndCity(filtri.getPrezzo(),filtri.getCoordinate(),filtri.getCategoria().toString(),filtri.getCity());}



       if(filtri.searchOnlyByNomeAndPrezzoAndCoordinateAndCategoriaAndCity(filtri)){return strutturaDAO.findByNomeAndPrezzoAndCoordinateAndCategoriaAndCity(filtri.getNome(),filtri.getPrezzo(),filtri.getCoordinate(),filtri.getCategoria().toString(),filtri.getCity());}

       return new LinkedList<>();
    }

    private static class Filtri{

        private String nome;
        private String city;
        private TipoStruttura categoria;
        private String coordinate;
        private int prezzo;

        public String getNome() {
            return nome;
        }

        public String getCity() {
            return city;
        }

        public TipoStruttura getCategoria() {
            return categoria;
        }

        public String getCoordinate() {
            return coordinate;
        }

        public int getPrezzo() {
            return prezzo;
        }

        private boolean searchOnlyByNome(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria == null) && (filtri.coordinate == null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCity(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate == null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCategoria(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCoordinate(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria == null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
        }

        //--------------------------------------------------------------------------------------------------------------------------

        private boolean searchOnlyByNomeAndCity(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate == null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndCategoria(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndCoordinate(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndPrezzo(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria == null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCityAndCategoria(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCityAndCoordinate(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCityAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCategoriaAndCoordinate(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCategoriaAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCoordinateAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
        }

        //---------------------------------------------------------------------------------------------------------------------------------

        private boolean searchOnlyByNomeAndCityAndCategoria(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndCityAndCoordinate(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndCityAndPrezzo(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCityAndCategoriaAndCoordinate(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCityAndCategoriaAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCategoriaAndCoordinateAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCategoriaAndNomeAndPrezzo(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCoordinateAndPrezzoAndNome(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCoordinateAndCityAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByNomeAndCategoriaAndCoordinate(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
        }

        //---------------------------------------------------------------------------------------------------------------------------------

        private boolean searchOnlyByNomeAndCityAndCategoriaAndCoordinate(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndCityAndCategoriaAndPrezzo(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate == null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByNomeAndCityAndPrezzoAndCoordinate(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByNomeAndPrezzoAndCoordinateAndCategoria(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByPrezzoAndCoordinateAndCategoriaAndCity(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
        }

        //---------------------------------------------------------------------------------------------------------------------------------

        private boolean searchOnlyByNomeAndPrezzoAndCoordinateAndCategoriaAndCity(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.coordinate != null) && (filtri.prezzo != -1);
        }











    }


}
