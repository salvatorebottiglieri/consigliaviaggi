package com.ingsw.consigliaviaggi.useCase;

import com.ingsw.consigliaviaggi.factories.FiltriFactory;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.interfaces.UseCaseRicercaStruttura;
import com.ingsw.consigliaviaggi.model.Filtri;

import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.stream.Collectors;

@Component
public class UseCaseRicercaStrutturaImpl implements UseCaseRicercaStruttura {


    private final FiltriFactory filtriFactory;
    private final StrutturaDAO strutturaDAO;

    public UseCaseRicercaStrutturaImpl(FiltriFactory filtriFactory,StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
        this.filtriFactory =filtriFactory;
    }

    @Override
    public List<Struttura> findStrutture(List<Filtri> filtri) {
        List<Filtri> researchers = filtriFactory.getFilters(filtri);
        Set<Struttura> result = new HashSet<>(researchers.get(0).research());
        for (Filtri researcher:researchers) {
            result = researcher.research().stream().distinct().filter(result::contains).collect(Collectors.toSet());
        }
        return new ArrayList<>(result);
    }

    @Override
    public Struttura getStruttura(String strutturaID) {
        Optional<Struttura> strutturaOptional = strutturaDAO.findById(strutturaID);
        return strutturaOptional.orElse(null);
    }

}
