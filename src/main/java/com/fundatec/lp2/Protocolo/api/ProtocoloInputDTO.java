package com.fundatec.lp2.Protocolo.api;

import javax.validation.constraints.NotBlank;

public class ProtocoloInputDTO {

    @NotBlank(message = "Campo protocolo é obrigatório!")
    private String numeroProtocolo;

    public String getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }
}
