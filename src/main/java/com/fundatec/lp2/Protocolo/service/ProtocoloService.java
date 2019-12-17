package com.fundatec.lp2.Protocolo.service;

import com.fundatec.lp2.Protocolo.api.ProtocoloInputDTO;
import com.fundatec.lp2.Protocolo.model.Protocolo;
import com.fundatec.lp2.Protocolo.repository.ProtocoloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtocoloService {

    private final ProtocoloRepository protocoloRepository;

    public ProtocoloService(ProtocoloRepository protocoloRepository) {
        this.protocoloRepository = protocoloRepository;
    }

    public Protocolo incluirProtocolo (ProtocoloInputDTO protocoloInputDTO) {
        Protocolo protocolo = new Protocolo();
        protocolo.setNumeroProtocolo(protocoloInputDTO.getNumeroProtocolo());
        return protocoloRepository.save(protocolo);
    }

    public List<Protocolo> getProtocolo () {
        return (List<Protocolo>) protocoloRepository.findAll();
    }

    public Protocolo atualizar (Long id, Protocolo protocoloParaAtualizar) {
        Protocolo protocolo = filtrarPorProtocolo(id);
        if (protocolo != null) {
            protocolo.setNumeroProtocolo(protocoloParaAtualizar.getNumeroProtocolo());
            protocolo.setSite(protocoloParaAtualizar.getSite());
            protocolo.setConcessionaria(protocoloParaAtualizar.getConcessionaria());
            protocolo.setUnidadeConsumidora(protocoloParaAtualizar.getUnidadeConsumidora());
            protocolo.setObservacoes(protocoloParaAtualizar.getObservacoes());
            protocolo = protocoloRepository.save(protocolo);
        }
        return protocolo;
    }

    public Protocolo filtrarPorProtocolo(Long id) {
        return protocoloRepository.findById(id).orElse(null);
    }

    public void exluirPorId (Long id) {
        protocoloRepository.deleteById(id);
    }
}
