package com.craftmanship.insurance.integration;

import com.craftmanship.insurance.InsuranceServicesApplication;
import com.craftmanship.insurance.model.PremiumRequestDTO;
import com.craftmanship.insurance.model.PremiumResponseDTO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PremiumCalculationIntegrationTest {
    public static final int NO_RISK_ZIP_CODE = 4000;
    public static final int STANDARD_POWER = 100;
    public static final int BONUS_MALUS_LEVEL = 9;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/" + uri;
    }

    @ParameterizedTest
    @CsvSource({" 0, 44.00",
            "1, 44.00",
            "2, 52.80",
            "3, 52.80",
            "4, 61.60",
            "5, 61.60",
            "6, 70.40",
            "8, 88.00",
            "9, 88.00",
            "10, 105.60",
            "11, 105.60",
            "12, 123.20",
            "13, 123.20",
            "14, 149.60",
            "15, 149.60",
            "16, 176.00",
            "17, 176.00"})
    public void calculatePremiumWithDifferentBonusMalusLevels(int bonusMalusLevel, String expectedPremium) {
        PremiumRequestDTO input = new PremiumRequestDTO(STANDARD_POWER, bonusMalusLevel, NO_RISK_ZIP_CODE, 3L);

        assertThat(callService(input)).isEqualTo(new BigDecimal(expectedPremium));
    }

    @ParameterizedTest
    @CsvSource({"10, 23.80",
            "27, 23.80",
            "28, 24.70",
            "146, 128.50",
            "147, 132.00",
            "200, 132.00"})
    public void calculatePremiumWithDifferentPowerRanges(int power, String expectedPremium) {
        PremiumRequestDTO input = new PremiumRequestDTO(power, 9, NO_RISK_ZIP_CODE, 3L);

        assertThat(callService(input)).isEqualTo(new BigDecimal(expectedPremium));
    }

    @ParameterizedTest
    @CsvSource({"1000, 83.60",
            "3333, 83.60",
            "3334, 88.00",
            "6666, 88.00",
            "6667, 92.40",
            "9999, 92.40"})
    public void calculatePremiumWithDifferentRiskLocations(int zipCode, String expectedPremium) {
        PremiumRequestDTO input = new PremiumRequestDTO(STANDARD_POWER, BONUS_MALUS_LEVEL, zipCode, 3L);

        assertThat(callService(input)).isEqualTo(new BigDecimal(expectedPremium));
    }

    private BigDecimal callService(PremiumRequestDTO input) {
        return restTemplate.postForEntity(
                        createURLWithPort("premium"),
                        input,
                        PremiumResponseDTO.class)
                .getBody()
                .premium();
    }

}
