package com.ingsw.consigliaviaggi.factories;

import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Filtri;
import com.ingsw.consigliaviaggi.model.FiltroName;
import com.ingsw.consigliaviaggi.model.FiltroPrezzo;
import com.ingsw.consigliaviaggi.model.FiltroTipoStruttura;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FiltriFactory {

    StrutturaDAO strutturaDAO;

    public FiltriFactory(StrutturaDAO strutturaDAO) {
        this.strutturaDAO = strutturaDAO;
    }

    public List<Filtri> getFilters(@NotNull List<Filtri> filterValues){
        List<Filtri> researcher = new ArrayList<>();

        for (Filtri filter:filterValues) {
            if(filter.getName().equalsIgnoreCase("NAME")){
                researcher.add(new FiltroName(filter.getName(),filter.getValue()));
            }else if(filter.getName().equalsIgnoreCase("CATEGORY")){
                researcher.add(new FiltroTipoStruttura(filter.getName(), filter.getValue()));
            }else if(filter.getName().equalsIgnoreCase("PRICE")){
                researcher.add(new FiltroPrezzo(filter.getName(), filter.getValue()));
            }
        }
        return  researcher;
    }
}
