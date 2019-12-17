package com.fundatec.lp2.Protocolo;

import com.fundatec.lp2.Protocolo.model.Protocolo;
import com.fundatec.lp2.Protocolo.repository.ProtocoloRepository;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExcluirProtocoloTest {

    @LocalServerPort
    private int randomPort;

    @Autowired
    private ProtocoloRepository protocoloRepository;

    private Protocolo protocolo;

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = randomPort;

        protocoloRepository.deleteAll();

        protocolo = new Protocolo();
        protocolo.setNumeroProtocolo("2019RXUEA");
        protocolo.setSite("U51003");
        protocolo.setUnidadeConsumidora("VIVO");
        protocolo.setConcessionaria("CEEE");
        protocolo.setObservacoes(".....");

        protocolo = protocoloRepository.save(protocolo);
    }

    @Test
    public void deveExcluirUmProtocolo () {
        RestAssured.given()
                .when()
                .delete("/protocolos/{id}", protocolo.getId())
                .then()
                .statusCode(HttpStatus.OK.value());

        Assert.assertEquals(0, protocoloRepository.count());
    }
}
