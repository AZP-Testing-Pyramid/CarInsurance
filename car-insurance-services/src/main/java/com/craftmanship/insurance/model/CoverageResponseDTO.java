package com.craftmanship.insurance.model;

import java.time.LocalDate;

public record CoverageResponseDTO (Long id, LocalDate validFrom, String description){
}
