package com.craftmanship.insurance.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.craftmanship.insurance.model.TaxRequestDTO;


class TaxServiceTest {

	private TaxService service = new TaxService();
	
	@ParameterizedTest
    @CsvSource({"130, 70, hybrid, 3.6",
    	"130, 70, electricity, 0.0",
    	"140, 80, diesel, 28.8",
    	"110, 65, gasoline, 7.2"})
    public void calculateTaxWithValidVehicleCharacteristics(int co2,int power, String fuelType, String expectedTax) {
        TaxRequestDTO taxRequestDTO = new TaxRequestDTO(co2, power, fuelType, LocalDate.now().minusMonths(1));
        final BigDecimal result = service.calculateTax(taxRequestDTO);

        assertThat(result).isNotNull();
        assertThat(result.doubleValue()).isEqualTo(new BigDecimal(expectedTax).doubleValue());

    }
}