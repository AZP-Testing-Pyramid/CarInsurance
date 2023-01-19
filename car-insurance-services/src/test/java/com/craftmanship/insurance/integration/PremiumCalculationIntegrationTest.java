package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.CoverageResponseDTO;
import com.craftmanship.insurance.model.PremiumRequestDTO;
import com.craftmanship.insurance.model.PremiumResponseDTO;
import com.craftmanship.insurance.model.TaxRequestDTO;
import com.craftmanship.insurance.model.TaxResponseDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PremiumCalculationIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/" + uri;
    }

    @Test
    public void calculatePremiumWithValidRESTContract() {
        PremiumRequestDTO premiumRequestDTO = new PremiumRequestDTO(Integer.valueOf(100), Integer.valueOf(5), Integer.valueOf(1140), Long.valueOf(1));

        ResponseEntity<PremiumResponseDTO> response = restTemplate.postForEntity(createURLWithPort("/premium"), premiumRequestDTO, PremiumResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().premium().longValue()).isEqualTo(57);

    }

    @Test
    public void calculateTaxWithValidRESTContract() {
        TaxRequestDTO taxRequestDTO = new TaxRequestDTO(Integer.valueOf(130), Integer.valueOf(70), "hybrid", LocalDate.now().minusMonths(1));

        ResponseEntity<TaxResponseDTO> response = restTemplate.postForEntity(createURLWithPort("/tax"), taxRequestDTO, TaxResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().tax().doubleValue()).isEqualTo(BigDecimal.valueOf(3.6).doubleValue());

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

    @Test
    public void shouldBecomeAUsefulTest() {
        // TODO: call the REST API and assert the result
        // - tip 1: either use Spring TestRestTemplate
        //          or REST Assured (https://rest-assured.io/)
        // - tip 2: use AssertJ to simplify the assertion code
        //      assertThat(result.statusCode()).isEqualTo(412);



    }

}
