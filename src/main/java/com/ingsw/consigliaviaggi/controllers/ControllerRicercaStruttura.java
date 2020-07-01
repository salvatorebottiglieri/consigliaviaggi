package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
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

    @PostMapping("/ricerca")
    public List<Struttura> ricercaStruttura(@RequestBody Filtri filtri){

       if(filtri.searchOnlyByNome(filtri)){ return strutturaDAO.findByNome(filtri.getNome()); }

       if(filtri.searchOnlyByCategoria(filtri)){return strutturaDAO.findByCategoria(filtri.getCategoria());}

       if(filtri.searchOnlyByCity(filtri)){return strutturaDAO.findByCity(filtri.getCity());}

       if(filtri.searchOnlyByCoordinate(filtri)){return strutturaDAO.findByCoordinate(filtri.getCoordinate()); }

       if(filtri.searchOnlyByPrezzo(filtri)){return strutturaDAO.findByPrezzo(filtri.getPrezzo());}


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
