package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.TaxRequestDTO;
import com.craftmanship.insurance.model.TaxResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaxCalculationIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/" + uri;
    }

    @Test
    public void shouldCalculateTaxWithValidData() {
        TaxRequestDTO input = new TaxRequestDTO(100, 100, "diesel", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal("28.80"));
    }

    private BigDecimal callService(TaxRequestDTO input) {
        return restTemplate.postForEntity(createURLWithPort("tax"), input, TaxResponseDTO.class).getBody().tax();
    }

}