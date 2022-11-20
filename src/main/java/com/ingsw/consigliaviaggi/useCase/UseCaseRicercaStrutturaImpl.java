package com.ingsw.consigliaviaggi.useCase;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.interfaces.UseCaseRicercaStruttura;
import com.ingsw.consigliaviaggi.model.Filtri;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UseCaseRicercaStrutturaImpl implements UseCaseRicercaStruttura {

    private final StrutturaDAO strutturaDAO;

    public UseCaseRicercaStrutturaImpl(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

    @Override
    public List<Struttura> findStruttura(Filtri filtri) {

        List<Struttura> result = new ArrayList<Struttura>();
        if( isOnlyOneFilterActive(filtri)){
            result =  findStrutturaByOneFilter(filtri);
        }
        else if (isOnlyTwoFiltersActive(filtri)){
            result =  findStrutturaByTwoFilters(filtri);
        }
        else if(isOnlyThreeFiltersActive(filtri)){
            result = findStrutturaByThreeFilters(filtri);
        }
        else if(isOnlyFourFiltersActive(filtri)){
            result = findStrutturaByFourFilters(filtri);
        }
        else if(isOnlyFiveFiltersActive(filtri)){
            result = findStrutturaByFiveFilters(filtri);
        }
        return result;
    }

    @Override
    public Struttura getStruttura(String strutturaID) {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaID);
        return strutturaOptional.orElse(null);
    }

    private CoppiaDiCoordinate getLatitudineAndLongitudineFromIndirizzo(Indirizzo indirizzo){

        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("cba750317df0437c8e39383df2284191");
        JOpenCageForwardRequest request =
                new JOpenCageForwardRequest(indirizzo.getVia(),indirizzo.getCivico().toString(),indirizzo.getCity());

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition(); // get the coordinate pair of the first result

        Double latitudine = firstResultLatLng.getLat();
        Double longitudine  =firstResultLatLng.getLng();

        return new CoppiaDiCoordinate(latitudine,longitudine);
    }
    private List<Struttura> getStruttureVicine(List<Struttura> strutture, Filtri filtri){

        List<Struttura> struttureAccettate = new LinkedList<>();

        for (Struttura struttura: strutture) {

            CoppiaDiCoordinate coppiaDiCoordinate = getLatitudineAndLongitudineFromIndirizzo(struttura.getIndirizzo());

            double distanzaTraUtenteEStruttura =
                    distanceBetweenLatLong(filtri.getLatitudine(),filtri.getLongitudine(),
                            coppiaDiCoordinate.getLatitudine(),
                            coppiaDiCoordinate.getLongitudine());

            if(distanzaTraUtenteEStruttura <= filtri.getDistanza()){
                struttureAccettate.add(struttura);
            }
        }
        return struttureAccettate;

    }
    private static double distanceBetweenLatLong(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371.01; //Kilometers
        return earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) +
                Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
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

    private List<Struttura> findStrutturaByFiveFilters(Filtri filtri){
        List<Struttura> struttureTrovatePerNomeAndPrezzoAndCategoriaAndCity
                = strutturaDAO.findByNomeAndCityAndCategoriaAndPrezzo(filtri.getNome(),
                filtri.getCity(),filtri.getCategoria().toString(),filtri.getPrezzo());
        return getStruttureVicine(struttureTrovatePerNomeAndPrezzoAndCategoriaAndCity,filtri);

    }

    private List<Struttura> findStrutturaByFourFilters(Filtri filtri){
        if(searchOnlyByNomeAndCityAndCategoriaAndCoordinate(filtri)){
            List<Struttura> struttureTrovatePerNomeAndCityAndCategoria =
                    strutturaDAO.findByNomeAndCityAndCategoria(filtri.getNome(),
                            filtri.getCity(),filtri.getCategoria().toString());
            return getStruttureVicine(struttureTrovatePerNomeAndCityAndCategoria,filtri);
        }

        if(searchOnlyByNomeAndCityAndCategoriaAndPrezzo(filtri)){
            return strutturaDAO.findByNomeAndCityAndCategoriaAndPrezzo(filtri.getNome(),filtri.getCity(),
                    filtri.getCategoria().toString(),filtri.getPrezzo());}

        if(searchOnlyByNomeAndCityAndPrezzoAndCoordinate(filtri)){
            List<Struttura> struttureTrovatePerNomeAndCityAndPrezzo=
                    strutturaDAO.findByNomeAndCityAndPrezzo(filtri.getNome(),filtri.getCity(),filtri.getPrezzo());
            return getStruttureVicine(struttureTrovatePerNomeAndCityAndPrezzo,filtri);
        }

        if(searchOnlyByNomeAndPrezzoAndCoordinateAndCategoria(filtri)){
            List<Struttura> struttureTrovatePerNomeAndPrezzoAndCategoria =
                    strutturaDAO.findByCategoriaAndNomeAndPrezzo(filtri.getCategoria(),
                            filtri.getNome(),filtri.getPrezzo());
            return getStruttureVicine(struttureTrovatePerNomeAndPrezzoAndCategoria,filtri);
        }

        if(searchOnlyByPrezzoAndCoordinateAndCategoriaAndCity(filtri)){
            List<Struttura> struttureTrovatePerPrezzoAndCategoriaAndCity =
                    strutturaDAO.findByCityAndCategoriaAndPrezzo(filtri.getCity(),filtri.getCategoria().toString(),
                            filtri.getPrezzo());
            return getStruttureVicine(struttureTrovatePerPrezzoAndCategoriaAndCity,filtri);
        }
        return new ArrayList<Struttura>();
    }

    private List<Struttura> findStrutturaByOneFilter(Filtri filtri){
        if(searchOnlyByNome(filtri)){ return strutturaDAO.findByNome(filtri.getNome());}
        if(searchOnlyByCategoria(filtri)){return strutturaDAO.findByCategoria(filtri.getCategoria());}
        if(searchOnlyByCity(filtri)){return strutturaDAO.findByCity(filtri.getCity());}
        if(searchOnlyByPrezzo(filtri)){return strutturaDAO.findByPrezzo(filtri.getPrezzo());}
        if(searchOnlyByCoordinate(filtri)){
            List<Struttura> strutture = (List<Struttura>) strutturaDAO.findAll();
            return getStruttureVicine(strutture,filtri);
        }
        return new ArrayList<>();
    }
    private List<Struttura> findStrutturaByThreeFilters(Filtri filtri){

        if(searchOnlyByNomeAndCityAndCategoria(filtri)){return strutturaDAO.
                findByNomeAndCityAndCategoria(filtri.getNome(),filtri.getCity(),filtri.getCategoria().toString());}

        if(searchOnlyByNomeAndCityAndCoordinate(filtri)) {
            List<Struttura> struttureTrovatePerNomeAndCity = strutturaDAO.
                    findByNomeAndCity(filtri.getNome(),filtri.getCity());
            return getStruttureVicine(struttureTrovatePerNomeAndCity,filtri);
        }

        if(searchOnlyByNomeAndCityAndPrezzo(filtri)){
            return strutturaDAO.findByNomeAndCityAndPrezzo(filtri.getNome(),filtri.getCity(),filtri.getPrezzo());}

        if(searchOnlyByCityAndCategoriaAndCoordinate(filtri)){
            List<Struttura> struttureTrovatePerCityAndCategoria =
                    strutturaDAO.findByCityAndCategoria(filtri.getCity(),filtri.getCategoria().toString());
            return getStruttureVicine(struttureTrovatePerCityAndCategoria,filtri);
        }
        if(searchOnlyByCityAndCategoriaAndPrezzo(filtri)){
            return strutturaDAO.findByCityAndCategoriaAndPrezzo(filtri.getCity(),filtri.getCategoria().toString(),
                    filtri.getPrezzo());}

        if(searchOnlyByCategoriaAndCoordinateAndPrezzo(filtri)){
            List<Struttura> struttureTrovatePerCategoriaAndPrezzo =
                    strutturaDAO.findByCategoriaAndPrezzo(filtri.getCategoria(),filtri.getPrezzo());
            return getStruttureVicine(struttureTrovatePerCategoriaAndPrezzo,filtri);
        }

        if(searchOnlyByCategoriaAndNomeAndPrezzo(filtri)){
            return strutturaDAO.findByCategoriaAndNomeAndPrezzo(filtri.getCategoria(),filtri.getNome(),
                    filtri.getPrezzo());}

        if(searchOnlyByCoordinateAndPrezzoAndNome(filtri)){
            List<Struttura> struttureTrovatePerPrezzoAndNome =
                    strutturaDAO.findByNomeAndPrezzo(filtri.getNome(),filtri.getPrezzo());
            return getStruttureVicine(struttureTrovatePerPrezzoAndNome,filtri);
        }

        if(searchOnlyByCoordinateAndCityAndPrezzo(filtri)){
            List<Struttura> struttureTrovatePerPrezzoAndCity =
                    strutturaDAO.findByCityAndPrezzo(filtri.getCity(),filtri.getPrezzo());
            return getStruttureVicine(struttureTrovatePerPrezzoAndCity,filtri);

        }

        if(searchOnlyByNomeAndCategoriaAndCoordinate(filtri)){
            List<Struttura> struttureTrovatePerNomeAndCategoria =
                    strutturaDAO.findByNomeAndCategoria(filtri.getNome(),filtri.getCategoria());
            return getStruttureVicine(struttureTrovatePerNomeAndCategoria,filtri);
        }
        return new ArrayList<Struttura>();
    }
    private List<Struttura> findStrutturaByTwoFilters(Filtri filtri){
        if(searchOnlyByNomeAndPrezzo(filtri)){return strutturaDAO.
                findByNomeAndPrezzo(filtri.getNome(),filtri.getPrezzo());}

        if(searchOnlyByCityAndCategoria(filtri)){return strutturaDAO.
                findByCityAndCategoria(filtri.getCity(),filtri.getCategoria().toString());}

        if(searchOnlyByCityAndCoordinate(filtri)){
            List<Struttura> struttureTrovatePerCity = strutturaDAO.findByCity(filtri.getCity());
            return getStruttureVicine(struttureTrovatePerCity,filtri);
        }

        if(searchOnlyByCityAndPrezzo(filtri)){return strutturaDAO.
                findByCityAndPrezzo(filtri.getCity(),filtri.getPrezzo());}

        if(searchOnlyByCategoriaAndCoordinate(filtri)){
            List<Struttura> struttureTrovatePerCategoria = strutturaDAO.findByCategoria(filtri.getCategoria());
            return getStruttureVicine(struttureTrovatePerCategoria,filtri);
        }

        if(searchOnlyByCategoriaAndPrezzo(filtri)){return strutturaDAO.
                findByCategoriaAndPrezzo(filtri.getCategoria(),filtri.getPrezzo());}

        if(searchOnlyByCoordinateAndPrezzo(filtri)){
            List<Struttura> struttureTrovatePerPrezzo = strutturaDAO.findByPrezzo(filtri.getPrezzo());
            return getStruttureVicine(struttureTrovatePerPrezzo,filtri);
        }
        if(searchOnlyByNomeAndCity(filtri)){return strutturaDAO.
                findByNomeAndCity(filtri.getNome(),filtri.getCity());}

        if(searchOnlyByNomeAndCategoria(filtri)){return strutturaDAO.
                findByNomeAndCategoria(filtri.getNome(),filtri.getCategoria());}

        if(searchOnlyByNomeAndCoordinate(filtri)){
            List<Struttura> struttureTrovatePerNome = strutturaDAO.findByNome(filtri.getNome());
            return getStruttureVicine(struttureTrovatePerNome,filtri);
        }
        return new ArrayList<Struttura>();
    }

    private boolean isOnlyOneFilterActive(Filtri filtri){
        return filtri.getNome() != null ^ (filtri.getCity() != null) ^ (filtri.getCategoria() != null) ^
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) ^ (filtri.getPrezzo() != -1);
    }

    private int numberOfFilterActive(Filtri filtri){
        int count = 0;
        if(filtri.getNome() != null)count+=1;
        if(filtri.getCategoria() != null)count+=1;
        if(filtri.getCity() != null)count+=1;
        if(filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1)count+=1;
        if(filtri.getPrezzo() != -1)count+=1;
        return count;
    }
    private boolean isOnlyTwoFiltersActive(Filtri filtri){
        return numberOfFilterActive(filtri) == 2;
    }
    private boolean isOnlyThreeFiltersActive(Filtri filtri){
        return numberOfFilterActive(filtri) == 3;
    }
    private boolean isOnlyFourFiltersActive(Filtri filtri){
        return numberOfFilterActive(filtri) == 4;
    }
    private boolean isOnlyFiveFiltersActive(Filtri filtri){
        return numberOfFilterActive(filtri) == 5;
    }



    public boolean searchOnlyByNome(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() == null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByCity(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() != null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByCategoria(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() == null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByCoordinate(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() == null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByPrezzo(Filtri filtri){

        return filtri.getNome() == null && (filtri.getCity() == null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() != -1);
    }
    //--------------------------------------------------------------------------------------------------------------
    public boolean searchOnlyByNomeAndCity(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() != null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByNomeAndCategoria(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() == null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByNomeAndCoordinate(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() == null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByNomeAndPrezzo(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() == null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByCityAndCategoria(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() != null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByCityAndCoordinate(Filtri filtri){

        return filtri.getNome() == null && (filtri.getCity() != null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByCityAndPrezzo(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() != null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByCategoriaAndCoordinate(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() == null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByCategoriaAndPrezzo(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() == null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByCoordinateAndPrezzo(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() == null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() != -1);
    }
    //--------------------------------------------------------------------------------------------------------------
    public boolean searchOnlyByNomeAndCityAndCategoria(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() != null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByNomeAndCityAndCoordinate(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() != null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByNomeAndCityAndPrezzo(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() != null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByCityAndCategoriaAndCoordinate(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() != null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByCityAndCategoriaAndPrezzo(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() != null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByCategoriaAndCoordinateAndPrezzo(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() == null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByCategoriaAndNomeAndPrezzo(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() == null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByCoordinateAndPrezzoAndNome(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() == null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByCoordinateAndCityAndPrezzo(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() != null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByNomeAndCategoriaAndCoordinate(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() == null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() == -1);
    }
    //--------------------------------------------------------------------------------------------------------------
    public boolean searchOnlyByNomeAndCityAndCategoriaAndCoordinate(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() != null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByNomeAndCityAndCategoriaAndPrezzo(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() != null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() == -1 && filtri.getLongitudine() == -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByNomeAndCityAndPrezzoAndCoordinate(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() != null) && (filtri.getCategoria() == null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() != -1);
    }
    public boolean searchOnlyByNomeAndPrezzoAndCoordinateAndCategoria(Filtri filtri){
        return filtri.getNome() != null && (filtri.getCity() == null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() == -1);
    }
    public boolean searchOnlyByPrezzoAndCoordinateAndCategoriaAndCity(Filtri filtri){
        return filtri.getNome() == null && (filtri.getCity() != null) && (filtri.getCategoria() != null) &&
                (filtri.getLatitudine() != -1 && filtri.getLongitudine() != -1) && (filtri.getPrezzo() != -1);
    }

}
