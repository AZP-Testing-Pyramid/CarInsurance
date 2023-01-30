package com.craftmanship.insurance.service;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.entities.Coverage;
import com.craftmanship.insurance.model.PremiumRequestDTO;
import com.craftmanship.insurance.model.PremiumResponseDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class PremiumServiceTest {

	private PremiumService service = new PremiumService();

	@ParameterizedTest
	@CsvSource({ "100, 5, 1140, 1, 57" })
	public void calculatePremium(int power, int bonusMalus, int zipCode, long coverageId, long premium) {
		PremiumRequestDTO premiumRequestDTO = new PremiumRequestDTO(Integer.valueOf(power), Integer.valueOf(bonusMalus),
				Integer.valueOf(zipCode), Long.valueOf(coverageId));
		final Coverage coverage = new Coverage();
		coverage.setDescription("Motor vehicle liability 2020");
		coverage.setId(1L);
		coverage.setMaxPremium(new BigDecimal("130"));
		coverage.setMinPremium(new BigDecimal("21.76"));
		coverage.setPercentagePremium(new BigDecimal("0.86"));
		coverage.setValidFrom(Date.valueOf("2020-01-01"));
		coverage.setValidTo(Date.valueOf("2020-12-31"));
		
		BigDecimal result = service.calculatePremium(premiumRequestDTO, coverage);

		assertThat(result).isNotNull();
		assertThat(result.longValue()).isEqualTo(premium);
	}
}