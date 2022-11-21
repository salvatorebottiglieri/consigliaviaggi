package com.ingsw.consigliaviaggi.useCase;

import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.interfaces.UseCaseVisualizzaRecensioni;
import com.ingsw.consigliaviaggi.model.Recensione;
import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UseCaseVisualizzaRecensioniImpl implements UseCaseVisualizzaRecensioni {

    StrutturaDAO strutturaDAO;

    public UseCaseVisualizzaRecensioniImpl(StrutturaDAO strutturaDAO){
        this.strutturaDAO = strutturaDAO;
    }

    public List<Recensione> getRecensioniRecenti(String idStruttura){

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(idStruttura);
        if(strutturaOptional.isPresent()) {
            Struttura struttura = strutturaOptional.get();
            List<Recensione> recensioni = struttura.getRecensioni();
            recensioni.sort(new Recensione.DataComparator());
            Collections.reverse( recensioni );
            return recensioni;
        }else{
            throw new EntityNotFoundException("Stuttura non trovata");
        }

    }

    @Override
    public List<Recensione> getRecensioniMenoRecenti(String strutturaId) {

        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        if (strutturaOptional.isPresent()) {
            Struttura struttura = strutturaOptional.get();
            List<Recensione> recensioni = struttura.getRecensioni();
            recensioni.sort(new Recensione.DataComparator());
            return recensioni;
        }else{
            throw new EntityNotFoundException("Stuttura non trovata");
        }
    }

    @Override
    public List<Recensione> getRecensioniPositive(String strutturaId) {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        if(strutturaOptional.isPresent()) {
            Struttura struttura = strutturaOptional.get();
            List<Recensione> recensioni = struttura.getRecensioni();
            recensioni.sort(new Recensione.VoteComparator());
            Collections.reverse(recensioni);
            return recensioni;
        }else{
            throw new EntityNotFoundException("Stuttura non trovata");
        }
    }

    @Override
    public List<Recensione> getRecensioniNegative(String strutturaId) {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaId);
        if (strutturaOptional.isPresent()) {
            Struttura struttura = strutturaOptional.get();
            List<Recensione> recensioni = struttura.getRecensioni();
            recensioni.sort(new Recensione.VoteComparator());
            return recensioni;
        }
        else{
            throw new EntityNotFoundException("Stuttura non trovata");
        }
    }
}
