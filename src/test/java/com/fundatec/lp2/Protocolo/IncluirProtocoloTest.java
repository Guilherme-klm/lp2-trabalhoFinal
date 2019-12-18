package com.fundatec.lp2.Protocolo;

import com.fundatec.lp2.Protocolo.api.ProtocoloInputDTO;
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
public class IncluirProtocoloTest {

    @LocalServerPort
    private int randomPort;

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = randomPort;

        protocoloRepository.deleteAll();
    }

    @Test
    public void deveIncluirUmProtocolo () {
        ProtocoloInputDTO protocoloInputDTO =  RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body("{\n" +
                        "\t\"numeroProtocolo\": \"2020TRAB\",\n" +
                        "\t\"site\": \"18RSSSN0291\",\n" +
                        "\t\"unidadeConsumidora\": \"VIVO\",\n" +
                        "\t\"concessionaria\": \"CEEE\",\n" +
                        "\t\"observacoes\": \"Acidente em um poste\"\n" +
                        "}")
                .when()
                .post("/protocolos")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ProtocoloInputDTO.class);

        Assert.assertEquals("2020TRAB", protocoloInputDTO.getNumeroProtocolo());
        Assert.assertEquals("18RSSSN0291", protocoloInputDTO.getSite());
        Assert.assertEquals("VIVO", protocoloInputDTO.getUnidadeConsumidora());
        Assert.assertEquals("CEEE", protocoloInputDTO.getConcessionaria());
        Assert.assertEquals("Acidente em um poste", protocoloInputDTO.getObservacoes());
    }
}
