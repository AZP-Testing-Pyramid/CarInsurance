package com.craftmanship.insurance.model;

public record PremiumRequestDTO(Integer power, Integer bonusMalus, Integer zipCode, Long coverageId) {
}
