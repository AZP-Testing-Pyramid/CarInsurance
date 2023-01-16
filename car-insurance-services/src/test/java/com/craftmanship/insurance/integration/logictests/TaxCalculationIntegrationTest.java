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

        TaxRequestDTO input = new TaxRequestDTO(100, 100, "electricity", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal("0.00"));
    }

    @ParameterizedTest
    @CsvSource({"165, 215, 144.00",
            "110, 135, 46.80",
            "65, 115, 7.20",})
    public void calculateTaxForGasoline(int power, int co2Emissions, String expectedTax) {

        TaxRequestDTO input = new TaxRequestDTO(co2Emissions, power, "gasoline", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal(expectedTax));
    }

    @ParameterizedTest
    @CsvSource({"165, 215, 144.00",
            "110, 135, 46.80",
            "65, 115, 7.20",})
    public void calculateTaxForDiesel(int power, int co2Emissions, String expectedTax) {

        TaxRequestDTO input = new TaxRequestDTO(co2Emissions, power, "diesel", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal(expectedTax));
    }

    @ParameterizedTest
    @CsvSource({"165, 215, 72.00",
            "110, 135, 32.40",
            "65, 115, 3.60",})
    public void calculateTaxForHybrid(int power, int co2Emissions, String expectedTax) {

        TaxRequestDTO input = new TaxRequestDTO(co2Emissions, power, "hybrid", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal(expectedTax));
    }

    private BigDecimal callService(TaxRequestDTO input) {

        var result =
                restTemplate.postForEntity(
                                createURLWithPort("/tax"),
                                input,
                                TaxResponseDTO.class)
                        .getBody()
                        .tax();
        return result;
    }

    @Test
    public void calculateTaxWithValidContract() {

        TaxRequestDTO input = new TaxRequestDTO(100, 100, "diesel", LocalDate.of(2022, 4, 21));

        assertThat(callService(input)).isEqualTo(new BigDecimal("28.80"));
    }

    @ParameterizedTest
    @CsvSource(
            value = {"null, 100, hybrid",
                    "100, null, hybrid",
                    "100, 100, null"},
            nullValues = {"null"}
    )
    public void calculateTaxWithInvalidParamsShouldReturnPreconditionFailed(Integer co2Emissions, Integer power, String fuelType) {
        TaxRequestDTO input = new TaxRequestDTO(co2Emissions, power, fuelType, LocalDate.now());

        var result = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/tax"));

        assertThat(result.statusCode()).isEqualTo(412);
    }

    @Test
    public void calculateTaxWithInvalidFuelTypeShouldReturnPreconditionFailed() {
        TaxRequestDTO input = new TaxRequestDTO(100, 100, "abc", LocalDate.now());

        var result = given()
                .contentType("application/json")
                .body(input)
                .when()
                .post(createURLWithPort("/tax"));

        assertThat(result.statusCode()).isEqualTo(412);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
