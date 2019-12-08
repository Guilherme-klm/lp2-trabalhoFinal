package com.fundatec.lp2.Protocolo.mapper;

import com.fundatec.lp2.Protocolo.api.ProtocoloInputDTO;
import com.fundatec.lp2.Protocolo.api.ProtocoloOutputDTO;
import com.fundatec.lp2.Protocolo.model.Protocolo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProtocoloMapper {

    public ProtocoloOutputDTO mapear (Protocolo protocolo) {
        ProtocoloOutputDTO protocoloOutputDTO =  new ProtocoloOutputDTO();
        protocoloOutputDTO.setNumeroProtocolo(protocolo.getNumeroProtocolo());

        return protocoloOutputDTO;
    }

    public Protocolo mapear (ProtocoloInputDTO protocoloInputDTO) {
        Protocolo protocolo = new Protocolo();
        protocolo.setNumeroProtocolo(protocoloInputDTO.getNumeroProtocolo());

        return protocolo;
    }

    public List<ProtocoloOutputDTO> mapear (List<Protocolo> protocolos) {
        List<ProtocoloOutputDTO> protocoloOutputDTOList =  new ArrayList();
        for (Protocolo protocolo: protocolos) {
            protocoloOutputDTOList.add(mapear(protocolo));
        }
        return protocoloOutputDTOList;
    }
}

