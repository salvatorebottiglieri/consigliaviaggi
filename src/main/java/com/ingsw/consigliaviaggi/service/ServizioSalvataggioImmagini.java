package com.ingsw.consigliaviaggi.service;

import com.ingsw.consigliaviaggi.dao.ImmagineDAO;
import com.ingsw.consigliaviaggi.exception.StorareImageException;
import com.ingsw.consigliaviaggi.model.Immagine;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class ServizioSalvataggioImmagini {

    private final ImmagineDAO immagineDAO;

    public ServizioSalvataggioImmagini(ImmagineDAO immagineDAO) {
        this.immagineDAO = immagineDAO;
    }


    public Immagine salvaImmagine(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new StorareImageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Immagine immagine = new Immagine(fileName, file.getContentType(), file.getBytes());

            return immagineDAO.save(immagine);

        } catch (IOException ex) {
            throw new StorareImageException("Impossibile salvare l'immagine" + fileName + ". Per favore, riprovare!");
        }
    }

    public Immagine getImmagine(String fileId) {



        Optional<Immagine> immagineOptional =  immagineDAO.findById(fileId);

        if(immagineOptional.isPresent()){

            return immagineOptional.get();
        }
        else{
                throw new EntityNotFoundException("Immagine non trovata");}
    }
}
