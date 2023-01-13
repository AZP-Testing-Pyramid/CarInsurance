package com.craftmanship.insurance.integration.logictests;

import com.craftmanship.insurance.InsuranceServicesApplication;
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

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PremiumCalculationIntegrationTest {
    public static final int NO_RISK_ZIP_CODE = 4000;
    public static final int STANDARD_POWER = 100;
    public static final int BONUS_MALUS_LEVEL = 9;
    @LocalServerPort
    private int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void calculatePremiumWithDifferentBonusMalusLevels() {
    }

    @Test
    public void calculatePremiumWithDifferentPowerRanges() {
    }

    @Test
    public void calculatePremiumWithDifferentRiskLocations() {
    }

    @Test
    public void calculatePremiumWithValidContract() {
    }

    @Test
    public void calculatePremiumWithInvalidParamsShouldReturnPreconditionFailed() {
    }

}
