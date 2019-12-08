package com.fundatec.lp2.Protocolo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Protocolo {

    @Id
    private String numeroProtocolo;

    public String getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }
}
