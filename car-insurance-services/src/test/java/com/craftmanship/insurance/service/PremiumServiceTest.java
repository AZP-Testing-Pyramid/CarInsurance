package com.craftmanship.insurance.service;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.entities.Coverage;
import com.craftmanship.insurance.model.PremiumRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PremiumServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    public static final int NO_RISK_ZIP_CODE = 4000;
    public static final int STANDARD_POWER = 100;
    public static final int BONUS_MALUS_LEVEL = 9;

   @Test
    public void calculatePremiumWithDifferentBonusMalusLevels() {
    }

    @Test
    public void calculatePremiumWithDifferentPowerRanges(int power, String expectedPremium) {
    }

    @Test
    public void calculatePremiumWithDifferentRiskLocations(int zipCode, String expectedPremium) {
    }
}