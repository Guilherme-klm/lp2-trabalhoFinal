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
        return protocoloRepository.findAll();
    }

    public Protocolo filtrarPorProtocolo(String nome) {
        return protocoloRepository.findById(nome).orElse(null);
    }
}
