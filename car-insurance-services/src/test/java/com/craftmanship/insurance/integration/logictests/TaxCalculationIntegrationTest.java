package com.craftmanship.insurance.integration.logictests;

import com.craftmanship.insurance.InsuranceServicesApplication;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaxCalculationIntegrationTest {
    @LocalServerPort
    private int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void calculateTaxForElectric() {
    }

    @Test
    public void calculateTaxForGasoline() {
    }

    @Test
    public void calculateTaxForDiesel() {
    }

    @Test
    public void calculateTaxForHybrid() {
    }

    @Test
    public void calculateTaxWithValidContract() {
    }

    @Test
    public void calculateTaxWithInvalidParamsShouldReturnPreconditionFailed() {
    }

    @Test
    public void calculateTaxWithInvalidFuelTypeShouldReturnPreconditionFailed() {
    }

}
