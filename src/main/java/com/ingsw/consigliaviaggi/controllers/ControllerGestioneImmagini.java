package com.ingsw.consigliaviaggi.controllers;

import com.ingsw.consigliaviaggi.model.Immagine;
import com.ingsw.consigliaviaggi.payload.UploadImageResponse;
import com.ingsw.consigliaviaggi.service.ServizioSalvataggioImmagini;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ControllerGestioneImmagini {


    private final  ServizioSalvataggioImmagini servizioSalvataggioImmagini;

    public ControllerGestioneImmagini(ServizioSalvataggioImmagini servizioSalvataggioImmagini) {
        this.servizioSalvataggioImmagini = servizioSalvataggioImmagini;
    }

    @PostMapping("/upload")
    public UploadImageResponse uploadFile(@RequestParam("file.jpeg") MultipartFile file) {
        Immagine immagine = servizioSalvataggioImmagini.salvaImmagine(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(immagine.getId())
                .toUriString();

        return new UploadImageResponse(immagine.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadImageResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {

        Immagine dbFile = servizioSalvataggioImmagini.getImmagine(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
}
