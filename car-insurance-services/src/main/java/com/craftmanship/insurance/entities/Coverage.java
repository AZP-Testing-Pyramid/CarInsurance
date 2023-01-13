package com.craftmanship.insurance.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "COVERAGE")
public class Coverage {
    private @Id
    @GeneratedValue
    Long id;

    @Column(name = "VALID_FROM", nullable = false)
    private Date validFrom;
    @Column(name = "VALID_TO", nullable = false)
    private Date validTo;
    @Column(name = "MIN_PREMIUM", nullable = false)
    private BigDecimal minPremium;
    @Column(name = "MAX_PREMIUM", nullable = false)
    private BigDecimal maxPremium;
    @Column(name = "PERCENTAGE_PREMIUM", nullable = false)
    private BigDecimal percentagePremium;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public BigDecimal getMinPremium() {
        return minPremium;
    }

    public void setMinPremium(BigDecimal minPremium) {
        this.minPremium = minPremium;
    }

    public BigDecimal getMaxPremium() {
        return maxPremium;
    }

    public void setMaxPremium(BigDecimal maxPremium) {
        this.maxPremium = maxPremium;
    }

    public BigDecimal getPercentagePremium() {
        return percentagePremium;
    }

    public void setPercentagePremium(BigDecimal percentagePremium) {
        this.percentagePremium = percentagePremium;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}