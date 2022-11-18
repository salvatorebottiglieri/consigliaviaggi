package com.ingsw.consigliaviaggi.config;

import com.ingsw.consigliaviaggi.interfaces.StrutturaDAO;
import com.ingsw.consigliaviaggi.model.Indirizzo;
import com.ingsw.consigliaviaggi.model.Struttura;
import com.ingsw.consigliaviaggi.model.TipoStruttura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    @Autowired
    private StrutturaDAO strutturaDAO;

    @PostConstruct
    private void postConstruct() {

        //ristoranti
        Indirizzo indirizzo = new Indirizzo("Via Toledo",20,"Napoli");
        Struttura struttura = new Struttura("Pizza e Babà","Antica pizzeria napoletana",indirizzo, TipoStruttura.ristorante,2,"https://res.cloudinary.com/sasi46/image/upload/v1601982463/getlstd-property-photo_rotated_90_hwqnoz.jpg");
        strutturaDAO.save(struttura);

         indirizzo = new Indirizzo("Vico Lungo Teatro Nuovo",103,"Napoli");
         struttura = new Struttura("Trattoria Nennella","Trattoria storica nel cuore di Napoli",indirizzo, TipoStruttura.ristorante,2,"https://res.cloudinary.com/sasi46/image/upload/v1601982682/asmegv7rqlxhwzdqm4jr.jpg");
        strutturaDAO.save(struttura);

        indirizzo = new Indirizzo("Piazza Vittoria",1,"Napoli");
        struttura = new Struttura("Terrazza Calabritto","Ristorante nel cuore del salotto di Napoli",indirizzo, TipoStruttura.ristorante,5,"https://res.cloudinary.com/sasi46/image/upload/v1601982861/Terrazza-Calabritto-gioiosa-mediterraneita-1800x600_kftbh5.jpg");
        strutturaDAO.save(struttura);

        //hotel

        indirizzo = new Indirizzo("Via Partenope",41,"Napoli");
        struttura = new Struttura("Hotel Excelsior","Tra i migliori hotel della città",indirizzo, TipoStruttura.hotel,5,"https://res.cloudinary.com/sasi46/image/upload/v1601983048/cr_foto-1-eurostars_hotel_excelsior_135879_jzqzkk.jpg");
        strutturaDAO.save(struttura);

        indirizzo = new Indirizzo("Via Partenope",46,"Napoli");
        struttura = new Struttura("Grand Hotel Santa Lucia","Hotel sul lungomare di Napoli",indirizzo, TipoStruttura.hotel,5,"https://res.cloudinary.com/sasi46/image/upload/v1601983215/20444083_b3b7rs.jpg");
        strutturaDAO.save(struttura);

        indirizzo = new Indirizzo("Via Montecalvario",15,"Napoli");
        struttura = new Struttura("Hotel Toledo","Hotel nel centro di Napoli",indirizzo, TipoStruttura.hotel,3,"https://res.cloudinary.com/sasi46/image/upload/v1601983398/hotel-toledo_jptaxd.jpg");
        strutturaDAO.save(struttura);

    }
}
