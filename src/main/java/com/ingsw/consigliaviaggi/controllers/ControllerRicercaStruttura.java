package com.ingsw.consigliaviaggi.controllers;


import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.ingsw.consigliaviaggi.dao.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerRicercaStruttura {

    private final StrutturaDAO strutturaDAO;

    public ControllerRicercaStruttura(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

    @GetMapping("/all/{strutturaID}")
    public Struttura getStruttura(@PathVariable String strutturaID){

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaID);
        if(strutturaOptional.isPresent()) {
            return strutturaOptional.get();
        }
        else{
            return null;
        }

    }


    @PostMapping("/all/ricerca")
    public List<Struttura> ricercaStruttura(@RequestBody Filtri filtri){

       if(filtri.searchOnlyByNome(filtri)){ return strutturaDAO.findByNome(filtri.getNome());}

       if(filtri.searchOnlyByCategoria(filtri)){return strutturaDAO.findByCategoria(filtri.getCategoria());}

       if(filtri.searchOnlyByCity(filtri)){return strutturaDAO.findByCity(filtri.getCity());}

       if(filtri.searchOnlyByCoordinate(filtri)){

           List<Struttura> strutture = (List<Struttura>) strutturaDAO.findAll();

           return getStruttureVicine(strutture,filtri);

       }

       if(filtri.searchOnlyByPrezzo(filtri)){return strutturaDAO.findByPrezzo(filtri.getPrezzo());}


       if(filtri.searchOnlyByNomeAndCity(filtri)){return strutturaDAO.findByNomeAndCity(filtri.getNome(),filtri.getCity());}

       if(filtri.searchOnlyByNomeAndCategoria(filtri)){return strutturaDAO.findByNomeAndCategoria(filtri.getNome(),filtri.getCategoria());}

       if(filtri.searchOnlyByNomeAndCoordinate(filtri)){

           List<Struttura> struttureTrovatePerNome = strutturaDAO.findByNome(filtri.getNome());

           return getStruttureVicine(struttureTrovatePerNome,filtri);

       }

       if(filtri.searchOnlyByNomeAndPrezzo(filtri)){return strutturaDAO.findByNomeAndPrezzo(filtri.getNome(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCityAndCategoria(filtri)){return strutturaDAO.findByCityAndCategoria(filtri.getCity(),filtri.getCategoria().toString());}

       if(filtri.searchOnlyByCityAndCoordinate(filtri)){

           List<Struttura> struttureTrovatePerCity = strutturaDAO.findByCity(filtri.getCity());

           return getStruttureVicine(struttureTrovatePerCity,filtri);
       }

       if(filtri.searchOnlyByCityAndPrezzo(filtri)){return strutturaDAO.findByCityAndPrezzo(filtri.getCity(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCategoriaAndCoordinate(filtri)){

           List<Struttura> struttureTrovatePerCategoria = strutturaDAO.findByCategoria(filtri.getCategoria());

           return getStruttureVicine(struttureTrovatePerCategoria,filtri);
       }

       if(filtri.searchOnlyByCategoriaAndPrezzo(filtri)){return strutturaDAO.findByCategoriaAndPrezzo(filtri.getCategoria(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCoordinateAndPrezzo(filtri)){

           List<Struttura> struttureTrovatePerPrezzo = strutturaDAO.findByPrezzo(filtri.getPrezzo());

           return getStruttureVicine(struttureTrovatePerPrezzo,filtri);
       }



       if(filtri.searchOnlyByNomeAndCityAndCategoria(filtri)){return strutturaDAO.findByNomeAndCityAndCategoria(filtri.getNome(),filtri.getCity(),filtri.getCategoria().toString());}

       if(filtri.searchOnlyByNomeAndCityAndCoordinate(filtri)) {

           List<Struttura> struttureTrovatePerNomeAndCity = strutturaDAO.findByNomeAndCity(filtri.getNome(),filtri.getCity());

           return getStruttureVicine(struttureTrovatePerNomeAndCity,filtri);
       }

       if(filtri.searchOnlyByNomeAndCityAndPrezzo(filtri)){return strutturaDAO.findByNomeAndCityAndPrezzo(filtri.getNome(),filtri.getCity(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCityAndCategoriaAndCoordinate(filtri)){

           List<Struttura> struttureTrovatePerCityAndCategoria = strutturaDAO.findByCityAndCategoria(filtri.getCity(),filtri.getCategoria().toString());

           return getStruttureVicine(struttureTrovatePerCityAndCategoria,filtri);
       }


       if(filtri.searchOnlyByCityAndCategoriaAndPrezzo(filtri)){return strutturaDAO.findByCityAndCategoriaAndPrezzo(filtri.getCity(),filtri.getCategoria().toString(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCategoriaAndCoordinateAndPrezzo(filtri)){

           List<Struttura> struttureTrovatePerCategoriaAndPrezzo = strutturaDAO.findByCategoriaAndPrezzo(filtri.getCategoria(),filtri.getPrezzo());

           return getStruttureVicine(struttureTrovatePerCategoriaAndPrezzo,filtri);
       }

       if(filtri.searchOnlyByCategoriaAndNomeAndPrezzo(filtri)){return strutturaDAO.findByCategoriaAndNomeAndPrezzo(filtri.getCategoria(),filtri.getNome(),filtri.getPrezzo());}

       if(filtri.searchOnlyByCoordinateAndPrezzoAndNome(filtri)){

           List<Struttura> struttureTrovatePerPrezzoAndNome = strutturaDAO.findByNomeAndPrezzo(filtri.getNome(),filtri.getPrezzo());

           return getStruttureVicine(struttureTrovatePerPrezzoAndNome,filtri);
       }

       if(filtri.searchOnlyByCoordinateAndCityAndPrezzo(filtri)){

           List<Struttura> struttureTrovatePerPrezzoAndCity = strutturaDAO.findByCityAndPrezzo(filtri.getCity(),filtri.getPrezzo());

           return getStruttureVicine(struttureTrovatePerPrezzoAndCity,filtri);

       }

       if(filtri.searchOnlyByNomeAndCategoriaAndCoordinate(filtri)){

           List<Struttura> struttureTrovatePerNomeAndCategoria = strutturaDAO.findByNomeAndCategoria(filtri.getNome(),filtri.getCategoria());

           return getStruttureVicine(struttureTrovatePerNomeAndCategoria,filtri);
       }



       if(filtri.searchOnlyByNomeAndCityAndCategoriaAndCoordinate(filtri)){

           List<Struttura> struttureTrovatePerNomeAndCityAndCategoria = strutturaDAO.findByNomeAndCityAndCategoria(filtri.getNome(),filtri.getCity(),filtri.getCategoria().toString());

           return getStruttureVicine(struttureTrovatePerNomeAndCityAndCategoria,filtri);
       }

       if(filtri.searchOnlyByNomeAndCityAndCategoriaAndPrezzo(filtri)){return strutturaDAO.findByNomeAndCityAndCategoriaAndPrezzo(filtri.getNome(),filtri.getCity(),filtri.getCategoria().toString(),filtri.getPrezzo());}

       if(filtri.searchOnlyByNomeAndCityAndPrezzoAndCoordinate(filtri)){

           List<Struttura> struttureTrovatePerNomeAndCityAndPrezzo= strutturaDAO.findByNomeAndCityAndPrezzo(filtri.getNome(),filtri.getCity(),filtri.getPrezzo());

           return getStruttureVicine(struttureTrovatePerNomeAndCityAndPrezzo,filtri);
       }

       if(filtri.searchOnlyByNomeAndPrezzoAndCoordinateAndCategoria(filtri)){

           List<Struttura> struttureTrovatePerNomeAndPrezzoAndCategoria = strutturaDAO.findByCategoriaAndNomeAndPrezzo(filtri.getCategoria(),filtri.getNome(),filtri.getPrezzo());

           return getStruttureVicine(struttureTrovatePerNomeAndPrezzoAndCategoria,filtri);
       }

       if(filtri.searchOnlyByPrezzoAndCoordinateAndCategoriaAndCity(filtri)){

           List<Struttura> struttureTrovatePerPrezzoAndCategoriaAndCity = strutturaDAO.findByCityAndCategoriaAndPrezzo(filtri.getCity(),filtri.getCategoria().toString(),filtri.getPrezzo());

           return getStruttureVicine(struttureTrovatePerPrezzoAndCategoriaAndCity,filtri);
       }



       if(filtri.searchOnlyByNomeAndPrezzoAndCoordinateAndCategoriaAndCity(filtri)){

           List<Struttura> struttureTrovatePerNomeAndPrezzoAndCategoriaAndCity = strutturaDAO.findByNomeAndCityAndCategoriaAndPrezzo(filtri.getNome(),filtri.getCity(),filtri.getCategoria().toString(),filtri.getPrezzo());

           return getStruttureVicine(struttureTrovatePerNomeAndPrezzoAndCategoriaAndCity,filtri);
       }

       throw new EntityNotFoundException("Nessuna struttura trovata");
    }

    private List<Struttura> getStruttureVicine(List<Struttura> strutture, Filtri filtri){

        List<Struttura> struttureAccettate = new LinkedList<>();

        for (Struttura struttura: strutture) {

            CoppiaDiCoordinate coppiaDiCoordinate = getLatitudineAndLongitudineFromIndirizzo(struttura.getIndirizzo());

            double distanzaTraUtenteEStruttura = distanceBetweenLatLong(filtri.latitudine,filtri.longitudine,coppiaDiCoordinate.getLatitudine(),coppiaDiCoordinate.getLongitudine());

            if(distanzaTraUtenteEStruttura <= filtri.getDistanza()){

                struttureAccettate.add(struttura);

            }

        }

        return struttureAccettate;

    }


    private CoppiaDiCoordinate getLatitudineAndLongitudineFromIndirizzo(Indirizzo indirizzo){

        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("cba750317df0437c8e39383df2284191");
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(indirizzo.getVia(),indirizzo.getCivico().toString(),indirizzo.getCity());

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition(); // get the coordinate pair of the first result

        Double latitudine = firstResultLatLng.getLat();
        Double longitudine  =firstResultLatLng.getLng();

        return new CoppiaDiCoordinate(latitudine,longitudine);
    }

    private static double distanceBetweenLatLong(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371.01; //Kilometers
        return earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
    }

    private static class CoppiaDiCoordinate{

        private Double latitudine;
        private Double longitudine;

        public CoppiaDiCoordinate(Double latitudine, Double longitudine) {
            this.latitudine = latitudine;
            this.longitudine = longitudine;
        }

        public Double getLatitudine() {
            return latitudine;
        }

        public void setLatitudine(Double latitudine) {
            this.latitudine = latitudine;
        }

        public Double getLongitudine() {
            return longitudine;
        }

        public void setLongitudine(Double longitudine) {
            this.longitudine = longitudine;
        }
    }



    private static class Filtri{

        private String nome;
        private String city;
        private TipoStruttura categoria;
        private Double latitudine;
        private Double longitudine;
        private int distanza;
        private int prezzo;

        public int getDistanza() {
            return distanza;
        }

        public void setDistanza(int distanza) {
            this.distanza = distanza;
        }

        public String getNome() {
            return nome;
        }

        public String getCity() {
            return city;
        }

        public TipoStruttura getCategoria() {
            return categoria;
        }

        public Double getLatitudine() {
            return latitudine;
        }

        public void setLatitudine(Double latitudine) {
            this.latitudine = latitudine;
        }

        public Double getLongitudine() {
            return longitudine;
        }

        public void setLongitudine(Double longitudine) {
            this.longitudine = longitudine;
        }

        public int getPrezzo() {
            return prezzo;
        }

        private boolean searchOnlyByNome(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria == null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCity(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCategoria(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCoordinate(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria == null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria == null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo != -1);
        }

        //--------------------------------------------------------------------------------------------------------------------------

        private boolean searchOnlyByNomeAndCity(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndCategoria(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndCoordinate(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria == null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndPrezzo(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria == null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCityAndCategoria(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCityAndCoordinate(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCityAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCategoriaAndCoordinate(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCategoriaAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCoordinateAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria == null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo != -1);
        }

        //---------------------------------------------------------------------------------------------------------------------------------

        private boolean searchOnlyByNomeAndCityAndCategoria(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndCityAndCoordinate(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndCityAndPrezzo(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCityAndCategoriaAndCoordinate(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByCityAndCategoriaAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCategoriaAndCoordinateAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city == null) && (filtri.categoria != null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCategoriaAndNomeAndPrezzo(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCoordinateAndPrezzoAndNome(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria == null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByCoordinateAndCityAndPrezzo(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria == null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByNomeAndCategoriaAndCoordinate(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo == -1);
        }

        //---------------------------------------------------------------------------------------------------------------------------------

        private boolean searchOnlyByNomeAndCityAndCategoriaAndCoordinate(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByNomeAndCityAndCategoriaAndPrezzo(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.latitudine == -1 && filtri.longitudine == -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByNomeAndCityAndPrezzoAndCoordinate(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria == null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo != -1);
        }
        private boolean searchOnlyByNomeAndPrezzoAndCoordinateAndCategoria(Filtri filtri){

            return filtri.nome != null && (filtri.city == null) && (filtri.categoria != null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo == -1);
        }
        private boolean searchOnlyByPrezzoAndCoordinateAndCategoriaAndCity(Filtri filtri){

            return filtri.nome == null && (filtri.city != null) && (filtri.categoria != null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo != -1);
        }

        //---------------------------------------------------------------------------------------------------------------------------------

        private boolean searchOnlyByNomeAndPrezzoAndCoordinateAndCategoriaAndCity(Filtri filtri){

            return filtri.nome != null && (filtri.city != null) && (filtri.categoria != null) && (filtri.latitudine != -1 && filtri.longitudine != -1) && (filtri.prezzo != -1);
        }











    }


}
