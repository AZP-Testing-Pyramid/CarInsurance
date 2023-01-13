package com.craftmanship.insurance.controller;

import com.craftmanship.insurance.model.CoverageResponseDTO;
import com.craftmanship.insurance.repositories.CoverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/coverage")
public class CoverageController {
    @Autowired
    private CoverageRepository coverageRepository;

    @GetMapping()
    public List<CoverageResponseDTO> findValidCoverage() {

        List<CoverageResponseDTO> result = coverageRepository.findAll().stream()
                .filter(coverage -> LocalDate.now().getYear() <= coverage.getValidTo().toLocalDate().getYear())
                .map(coverage -> new CoverageResponseDTO(coverage.getId(), coverage.getValidFrom().toLocalDate(), coverage.getDescription()))
                .toList();

        return result;
    }
}
