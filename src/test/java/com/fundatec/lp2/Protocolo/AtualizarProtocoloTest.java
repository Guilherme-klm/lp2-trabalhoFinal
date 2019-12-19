package com.fundatec.lp2.Protocolo;

import com.fundatec.lp2.Protocolo.dto.ProtocoloOutputDTO;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class AtualizarProtocoloTest {

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
        protocolo.setNumeroProtocolo("233121235764530");
        protocolo.setSite("U51003");
        protocolo.setUnidadeConsumidora("VIVO");
        protocolo.setConcessionaria("CEEE");
        protocolo.setObservacoes(".....");

        protocolo = protocoloRepository.save(protocolo);
    }

    @Test
    public void deveAtualizarUmProtocolo () {
        ProtocoloOutputDTO protocoloOutputDTO = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body("{\n" +
                        "\t\"numeroProtocolo\": \"233121235764534\",\n" +
                        "\t\"site\": \"RSROD0193\",\n" +
                        "\t\"unidadeConsumidora\": \"CLARO\",\n" +
                        "\t\"concessionaria\": \"CEEE\",\n" +
                        "\t\"observacoes\": \"Houve um acidente\"\n" +
                        "}")
                .when()
                .put("/protocolos/{id}", protocolo.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ProtocoloOutputDTO.class);

        Assert.assertEquals("233121235764534", protocoloOutputDTO.getNumeroProtocolo());
        Assert.assertEquals("RSROD0193", protocoloOutputDTO.getSite());
        Assert.assertEquals("CLARO", protocoloOutputDTO.getUnidadeConsumidora());
        Assert.assertEquals("CEEE", protocoloOutputDTO.getConcessionaria());
        Assert.assertEquals("Houve um acidente", protocoloOutputDTO.getObservacoes());

    }
}