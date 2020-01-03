package com.ts.codemetrics.service.cqprovider.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QualityGatewayScanResult {
    private Integer violations;
    private Integer newViolations;
    private Integer openIssues;
    private Integer codeSmells;
    private Integer newCodeSmells;
    private String maintainabilityRating;
    private BigDecimal technicalDebt;
    private BigDecimal newTechnicalDebt;
    private BigDecimal technicalDebtRatio;
    private BigDecimal newTechnicalDebtRatio;
    private String qualityGatewayStatus;
    private Integer bugs;
    private Integer newBugs;
    private String reliabilityRating;
    private Integer vulnerabilities;
    private Integer newVulnerabilities;
    private String securityRating;
    private Long linesofCode;
    private BigDecimal codeCoverage;
    private BigDecimal newCodeCoverage;
    private Integer noofUnitTests;
    private Integer noofUnitTestsFailed;
    private Integer noofUnitTestsPassed;
    private BigDecimal unitTestSuccessDensity;
    private String response;
}
