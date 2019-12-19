package com.fundatec.lp2.Protocolo.api;

import com.fundatec.lp2.Protocolo.dto.ErroDTO;
import com.fundatec.lp2.Protocolo.dto.ProtocoloInputDTO;
import com.fundatec.lp2.Protocolo.dto.ProtocoloOutputDTO;
import com.fundatec.lp2.Protocolo.mapper.ProtocoloMapper;
import com.fundatec.lp2.Protocolo.model.Protocolo;
import com.fundatec.lp2.Protocolo.service.ProtocoloService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @PostMapping("/protocolos")
    @ApiOperation(value = "Inclui Protocolo", 
                  notes = "Inclui um protocolo no banco de dados. Faz a validação dos campos necessários a serem preenchidos")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Protocolo incluído com sucesso!", response = ProtocoloOutputDTO.class)})
    public ResponseEntity<?> postProtocolos (@Valid @RequestBody ProtocoloInputDTO protocoloInputDTO) {
        try {
            Protocolo protocolo = protocoloMapper.mapear(protocoloInputDTO);
            protocolo = protocoloService.incluirProtocolo(protocolo);
            ProtocoloOutputDTO protocoloOutputDTO = protocoloMapper.mapear(protocolo);
            return ResponseEntity.status(HttpStatus.CREATED).body(protocoloOutputDTO);
        } catch (RuntimeException e) {
            ErroDTO erroDTO = new ErroDTO();
            erroDTO.setMensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(erroDTO);
        }
    }

    @PutMapping("/protocolos/{id}")
    @ApiOperation(value = "Edita Protocolo", notes = "Edita um certo protocolo quando inserido o ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Protocolo Atualizado com sucesso!", response = ProtocoloOutputDTO.class)})
    public ResponseEntity<?> atualizarProtocolo (@PathVariable Long id,
                                                 @Valid @RequestBody ProtocoloInputDTO protocoloInputDTO) {

        Protocolo protocolo = protocoloMapper.mapear(protocoloInputDTO);
        protocolo = protocoloService.atualizar(id, protocolo);

        if (protocolo == null) {
            return ResponseEntity.noContent().build();
        }
        ProtocoloOutputDTO protocoloOutputDTO = protocoloMapper.mapear(protocolo);
        return ResponseEntity.ok().body(protocoloOutputDTO);
    }

    @GetMapping("/protocolos")
    @ApiOperation(value = "Busca Protocolos", notes = "Busca todos os protocolos do banco de dados")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Protocolos encontrados!", response = ProtocoloOutputDTO.class)})
    public ResponseEntity<?> getProtocolos () {
        List<Protocolo> protocoloOutputDTOList = protocoloService.getProtocolo();
        return getResponseEntity(protocoloOutputDTOList);
    }

    @GetMapping("/protocolos/{id}")
    @ApiOperation(value = "Busca Protocolo", notes = "Busca um certo protocolo quando inserido o ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Achei!", response = ProtocoloOutputDTO.class)})
    public ResponseEntity<?> getProtocoloId (@PathVariable Long id) {
        Protocolo protocolo = protocoloService.filtrarPorProtocolo(id);

        if (protocolo == null) {
            return ResponseEntity.noContent().build();
        } else {
            ProtocoloOutputDTO protocoloOutputDTO = protocoloMapper.mapear(protocolo);
            return ResponseEntity.ok(protocoloOutputDTO);
        }
    }

    private ResponseEntity<?> getResponseEntity(Protocolo protocolo) {
        if (protocolo == null) {
            return ResponseEntity.noContent().build();
        } else {
            ProtocoloOutputDTO protocoloOutputDTO = protocoloMapper.mapear(protocolo);
            return ResponseEntity.ok(protocoloOutputDTO);
        }
    }

    @DeleteMapping("/protocolos/{id}")
    @ApiOperation(value = "Protocolo Carro", notes = "Deleta um certo protocolo quando inserido o ID")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Protocolo deletado com sucesso!")})
    public ResponseEntity<?> excluirProtocolo (@PathVariable Long id) {
        protocoloService.exluirPorId(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> getResponseEntity(List<Protocolo> protocolos) {
        if (protocolos.size() == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(protocolos);
        }
    }
}
