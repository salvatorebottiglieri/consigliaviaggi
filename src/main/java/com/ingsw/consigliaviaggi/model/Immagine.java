package com.ingsw.consigliaviaggi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Immagine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;
    private String fileType;
    @ManyToOne
    @JoinColumn
    private Struttura strutturaFoto;
    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] data;

    public Immagine() { }

    public Immagine(String fileName, String fileType, byte[] data, Struttura struttura) {

        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.strutturaFoto = struttura;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getId() {
        return this.id;
    }


    public Struttura getStruttura() {
        return strutturaFoto;
    }

    public void setStruttura(Struttura struttura) {
        this.strutturaFoto = struttura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Immagine immagine = (Immagine) o;
        return id.equals(immagine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
