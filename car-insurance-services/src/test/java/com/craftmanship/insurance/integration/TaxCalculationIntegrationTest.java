package com.craftmanship.insurance.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

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

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.TaxRequestDTO;
import com.craftmanship.insurance.model.TaxResponseDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaxCalculationIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


    @ParameterizedTest
    @CsvSource({"130, 70, hybrid, 3.6",
    	"130, 70, electricity, 0.0",
    	"140, 80, diesel, 28.8",
    	"110, 65, gasoline, 7.2"})
    public void calculateTaxWithValidVehicleCharacteristics(int co2,int power, String fuelType, String expectedTax) {
        TaxRequestDTO taxRequestDTO = new TaxRequestDTO(co2, power, fuelType, LocalDate.now().minusMonths(1));

        ResponseEntity<TaxResponseDTO> response = restTemplate.postForEntity(createURLWithPort("/tax"), taxRequestDTO, TaxResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().tax().doubleValue()).isEqualTo(new BigDecimal(expectedTax) .doubleValue());

    }

}
