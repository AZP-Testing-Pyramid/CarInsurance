package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.CoverageResponseDTO;
import com.craftmanship.insurance.model.TaxRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoverageIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


    @Test
    public void calculateCoverageWithValidRESTContract() {


        ResponseEntity<CoverageResponseDTO[]> response = restTemplate.getForEntity(createURLWithPort("/coverage"),CoverageResponseDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        CoverageResponseDTO[] body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body).isNotEmpty();
        CoverageResponseDTO coverageResponseDTO = body[0];

        assertThat(coverageResponseDTO.id()).isEqualTo(4);
    }

}
