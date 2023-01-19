package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.service.TaxService;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.craftmanship.insurance.model.*;

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

    @Test
    public void testElectric() {
    	testTaxCalculation(0, 200, "electricity", "0.00");
    }

    @CsvSource({
    	"100,25.20",
    	"200,97.20",
    	"69,3.60",
    	"70,3.60"    	
    })
    @ParameterizedTest
    public void testHybrid(final int power, final String result) {
    	testTaxCalculation(0, power, "hybrid", result);
    }
    
    @MethodSource("provideCo2RelevantCases")
    @ParameterizedTest
    public void testGasoline(final int co2, final int power, final String result) {
    	testTaxCalculation(co2, power, "gasoline", result);
    }
    
    @MethodSource("provideCo2RelevantCases")
    @ParameterizedTest
    public void testDiesel(final int co2, final int power, final String result) {
    	testTaxCalculation(co2, power, "diesel", result);
    }

	private void testTaxCalculation(int co2Emissions, int power, final String fuelType, final String result) {
		TaxRequestDTO request = new TaxRequestDTO(co2Emissions, power, fuelType, LocalDate.now());
		
		ResponseEntity<TaxResponseDTO> res = restTemplate.postForEntity(createURLWithPort("/tax"), //
				request, //
    			TaxResponseDTO.class);
		
    	Assertions.assertThat(res.getBody().tax()).isEqualTo(new BigDecimal(result));
	}
    
	private static Stream<Arguments> provideCo2RelevantCases() {
	    return Stream.of(
	      Arguments.of(120, 100, "28.80"),
	      Arguments.of(119, 200,"100.80"),
	      Arguments.of(200, 69, "64.80"),
	      Arguments.of(200, 70, "64.80"),
	      Arguments.of(120, 69, "7.20"),
	      Arguments.of(119, 70, "7.20")
	    );
	} 

}
