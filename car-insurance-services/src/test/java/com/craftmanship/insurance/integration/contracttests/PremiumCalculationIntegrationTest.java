package com.craftmanship.insurance.integration.contracttests;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.CoverageResponseDTO;
import com.craftmanship.insurance.model.PremiumRequestDTO;
import com.craftmanship.insurance.model.PremiumResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PremiumCalculationIntegrationTest {

    @LocalServerPort
    private final int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void calculatePremiumWithValidRESTContract() {
        PremiumRequestDTO input = new PremiumRequestDTO(100, 9, 4000,3L);

        PremiumResponseDTO result =
                restTemplate.postForEntity(
                                createURLWithPort("/premium"),
                                input,
                                PremiumResponseDTO.class)
                        .getBody();


        assertThat(result.premium()).isEqualTo(new BigDecimal("88.00"));
    }

    @ParameterizedTest
    @CsvSource(
            value = {"null, 9, 1000",
                    "100, null, 1000",
                    "100, 9, null"},
            nullValues = {"null"}
    )
    public void calculatePremiumWithNullValuesShouldReturnPreconditionFailed(Integer power, Integer bonusMalus, Integer zipCode) {
        PremiumRequestDTO input = new PremiumRequestDTO(power, bonusMalus, zipCode,3L);

        var result =
                restTemplate.postForEntity(
                        createURLWithPort("/premium"),
                        input,
                        String.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(412));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
