package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.CoverageResponseDTO;
import com.craftmanship.insurance.model.PremiumRequestDTO;
import com.craftmanship.insurance.model.PremiumResponseDTO;
import com.craftmanship.insurance.model.TaxRequestDTO;
import com.craftmanship.insurance.model.TaxResponseDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest
    @CsvSource({"100, 5, 1140, 1, 57"})
    public void calculatePremium(int power, int bonusMalus, int zipCode, long coverageId, long premium) {
        PremiumRequestDTO premiumRequestDTO = new PremiumRequestDTO(Integer.valueOf(power), Integer.valueOf(bonusMalus), Integer.valueOf(zipCode), Long.valueOf(coverageId));

        ResponseEntity<PremiumResponseDTO> response = restTemplate.postForEntity(createURLWithPort("/premium"), premiumRequestDTO, PremiumResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().premium().longValue()).isEqualTo(premium);

    }
}
