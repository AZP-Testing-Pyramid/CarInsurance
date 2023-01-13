package com.craftmanship.insurance.service;

import com.craftmanship.insurance.model.TaxRequestDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.BigDecimal.valueOf;

@Service
public class TaxService {

    public static final BigDecimal TAX_RATE = valueOf(0.72);

    public BigDecimal calculateTax(TaxRequestDTO input) {

        if (input.fuelType().equals("electricity")) {
            return BigDecimal.ZERO.setScale(2);
        }
        int co2Param = Math.max(input.co2Emissions() - 115, 5);
        if (input.fuelType().equals("hybrid")) {
            co2Param = 0;
        }

        int powerParam = Math.max(input.power() - 65, 5);

        return valueOf(co2Param + powerParam).multiply(TAX_RATE);
    }

}
