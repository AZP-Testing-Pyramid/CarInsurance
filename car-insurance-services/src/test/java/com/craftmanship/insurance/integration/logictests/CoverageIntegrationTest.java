package com.craftmanship.insurance.integration.logictests;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.CoverageResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoverageIntegrationTest {
    @LocalServerPort
    private int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReadOnlyValidCoverageRessources() {
        // TODO: use Spring Boot Test to call the server
            //FOr a list
        //        var result = restTemplate.getForEntity(
        //             http://localhost:" + port + "/coverage"";,
        //              CoverageResponseDTO[].class)
        //     .getBody();
        // TODO: add your assertion(s)
        // - tip: use either REST Assured or AssertJ to simplify the assertion code
    }
}
