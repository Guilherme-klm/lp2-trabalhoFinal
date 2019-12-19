package com.fundatec.lp2.Protocolo.dto;

import javax.validation.constraints.*;

public class ProtocoloInputDTO {

    @NotEmpty(message="Este campo não pode ser vazio")
    @NotNull(message = "Não pode ser nulo")
    @Pattern(regexp = "^[0-9]{15}$", message = "Protocolo inválido")
    private String numeroProtocolo;
    
    @NotEmpty(message="Este campo não pode ser vazio")
    @NotNull(message = "Não pode ser nulo")
    private String site;
    
    @NotEmpty(message="Este campo não pode ser vazio")
    @NotNull(message = "Não pode ser nulo")
    private String unidadeConsumidora;
    
    @NotEmpty(message="Este campo não pode ser vazio")
    @NotNull(message = "Não pode ser nulo")
    private String concessionaria;
    
    @Size(max=350, message= "Não pode ter mais de 350 caracteres")
    private String observacoes;

    public String getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUnidadeConsumidora() {
        return unidadeConsumidora;
    }

    public void setUnidadeConsumidora(String unidadeConsumidora) {
        this.unidadeConsumidora = unidadeConsumidora;
    }

    public String getConcessionaria() {
        return concessionaria;
    }

    public void setConcessionaria(String concessionaria) {
        this.concessionaria = concessionaria;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
