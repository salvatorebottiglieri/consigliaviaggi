package com.ingsw.consigliaviaggi.interfaces;

import com.ingsw.consigliaviaggi.model.Recensione;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UseCaseVisualizzaRecensioni {

    List<Recensione> getRecensioniRecenti(String idStruttura);
    List<Recensione> getRecensioniMenoRecenti( String strutturaId);
    List<Recensione> getRecensioniPositive( String strutturaId);
    List<Recensione> getRecensioniNegative( String strutturaId);
}
