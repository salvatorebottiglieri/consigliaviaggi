package com.ingsw.consigliaviaggi.useCase;

import com.ingsw.consigliaviaggi.exception.ElementIsAlreadyPresentExcetpion;
import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.interfaces.UseCaseAggiungiStruttura;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UseCaseAggiungiStrutturaImpl implements UseCaseAggiungiStruttura {


    private final StrutturaDAO strutturaDAO;

    @Autowired
    public UseCaseAggiungiStrutturaImpl(StrutturaDAO strutturaDAO){
        this.strutturaDAO = strutturaDAO;
    }

    @Override
    public boolean creaStruttura(Struttura struttura) throws ElementIsAlreadyPresentExcetpion {
        Indirizzo indirizzo = new Indirizzo(struttura.getIndirizzo().getVia(), struttura.getIndirizzo().
                getCivico(), struttura.getIndirizzo().getCity());
        Struttura nuovaStruttura = new Struttura(struttura.getNome(), struttura.getDescrizione(),
                indirizzo, struttura.getCategoria(), struttura.getPrezzo(),struttura.getFoto());

        if (!strutturaDAO.existsStrutturaByIdEquals(struttura.getId())) {
            strutturaDAO.save(nuovaStruttura);
            return true;
        }
        else{
            throw new ElementIsAlreadyPresentExcetpion("Struttura già presente");
        }
    }
}
