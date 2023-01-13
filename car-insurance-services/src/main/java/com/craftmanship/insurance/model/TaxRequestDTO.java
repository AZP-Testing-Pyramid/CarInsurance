package com.craftmanship.insurance.model;

import java.time.LocalDate;

public record TaxRequestDTO(Integer co2Emissions, Integer power, String fuelType, LocalDate firstRegistration) {
}
