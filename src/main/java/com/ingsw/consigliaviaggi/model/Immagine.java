package com.ingsw.consigliaviaggi.model;

import javax.persistence.*;

@Entity
public class Immagine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    public Immagine() {

    }

    public Immagine(String fileName, String fileType, byte[] data) {

        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
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

    public String getId() {
        return this.id;
    }
}
