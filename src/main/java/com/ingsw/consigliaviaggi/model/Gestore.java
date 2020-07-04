package com.ingsw.consigliaviaggi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Gestore {

    private String id;
    private String password;
    private boolean isActive;
    private String ruolo;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
