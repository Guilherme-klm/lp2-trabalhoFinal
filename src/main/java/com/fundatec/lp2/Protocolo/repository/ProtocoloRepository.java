package com.fundatec.lp2.Protocolo.repository;

import com.fundatec.lp2.Protocolo.model.Protocolo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProtocoloRepository extends CrudRepository<Protocolo, String> {

    public List<Protocolo> findAll ();

}
