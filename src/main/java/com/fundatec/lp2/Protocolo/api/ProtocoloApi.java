package com.fundatec.lp2.Protocolo.api;

import com.fundatec.lp2.Protocolo.mapper.ProtocoloMapper;
import com.fundatec.lp2.Protocolo.model.Protocolo;
import com.fundatec.lp2.Protocolo.service.ProtocoloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProtocoloApi {

    private final ProtocoloService protocoloService;
    private final ProtocoloMapper protocoloMapper;

    public ProtocoloApi(ProtocoloService protocoloService, ProtocoloMapper protocoloMapper) {
        this.protocoloService = protocoloService;
        this.protocoloMapper = protocoloMapper;
    }

    @PostMapping("/protocolo")
    public ResponseEntity<?> postProtocolo (@Valid @RequestBody ProtocoloInputDTO protocoloInputDTO) {
        try {
            Protocolo protocolo = protocoloMapper.mapear(protocoloInputDTO);
            ProtocoloOutputDTO protocoloOutputDTO = protocoloMapper.mapear(protocolo);
            return ResponseEntity.status(HttpStatus.CREATED).body(protocoloOutputDTO);
        } catch (RuntimeException e) {
            ErroDTO erroDTO = new ErroDTO();
            erroDTO.setMensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(erroDTO);
        }
    }

    @GetMapping("/protocolo")
    public ResponseEntity<?> getProtocolo () {
        List<Protocolo> protocoloOutputDTOList = protocoloService.getProtocolo();
        return getResponseEntity(protocoloOutputDTOList);
    }

    @GetMapping("/protocolo/{id}")
    public ResponseEntity<ProtocoloOutputDTO> getProtocoloId (@PathVariable String nomeProtocolo) {
        Protocolo protocolo = protocoloService.filtrarPorProtocolo(nomeProtocolo);

        if (protocolo == null) {
            return ResponseEntity.noContent().build();
        } else {
            ProtocoloOutputDTO protocoloOutputDTO = protocoloMapper.mapear(protocolo);
            return ResponseEntity.ok(protocoloOutputDTO);
        }
    }

    private ResponseEntity<?> getResponseEntity(List<Protocolo> protocolos) {
        if (protocolos.size() == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(protocolos);
        }
    }
}