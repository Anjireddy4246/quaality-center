package com.ts.codemetrics.service.provider.cqprovider.sonarqube;

import com.ts.codemetrics.service.provider.cqprovider.model.QualityGatewayScanResult;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

public class ResponseMapper {

    public static Optional<QualityGatewayScanResult> mapSonarResponseToQualityGatewayResponse(SonarScanResult sonarScanResult) {
        if (Objects.nonNull(sonarScanResult) && !CollectionUtils.isEmpty(sonarScanResult.getMeasures())) {
            QualityGatewayScanResult qualityGatewayScanResult = new QualityGatewayScanResult();
            HashMap<String, Object> sonarMetrics = extractSonarValues(sonarScanResult.getMeasures());
            qualityGatewayScanResult.setComplexity(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.COMPLEXITY)));
            qualityGatewayScanResult.setCognitiveComplexity(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.COGNITIVE_COMPLEXITY)));
            qualityGatewayScanResult.setViolations(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.VIOLATIONS)));
            qualityGatewayScanResult.setNewViolations(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.NEW_VIOLATIONS)));
            qualityGatewayScanResult.setOpenIssues(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.OPEN_ISSUES)));
            qualityGatewayScanResult.setCodeSmells(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.CODE_SMELLS)));
            qualityGatewayScanResult.setNewCodeSmells(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.NEW_CODE_SMELLS)));
            qualityGatewayScanResult.setMaintainabilityRating(getValueByKey(sonarMetrics, SonarMetricDefinition.MAINTAINABILITY_RATING));
            qualityGatewayScanResult.setTechnicalDebt(parseDecimal(getValueByKey(sonarMetrics, SonarMetricDefinition.TECHNICAL_DEBT)));
            qualityGatewayScanResult.setNewTechnicalDebt(parseDecimal(getValueByKey(sonarMetrics, SonarMetricDefinition.TECHNICAL_DEBT_NEW_CODE)));
            qualityGatewayScanResult.setTechnicalDebtRatio(parseDecimal(getValueByKey(sonarMetrics, SonarMetricDefinition.TECHNICAL_DEBT_RATIO)));
            qualityGatewayScanResult.setNewTechnicalDebtRatio(parseDecimal(getValueByKey(sonarMetrics, SonarMetricDefinition.NEW_TECHNICAL_DEBT_RATIO)));
            qualityGatewayScanResult.setQualityGatewayStatus(getValueByKey(sonarMetrics, SonarMetricDefinition.QUALITY_GATE_STATUS));
            qualityGatewayScanResult.setBugs(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.BUGS)));
            qualityGatewayScanResult.setNewBugs(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.NEW_BUGS)));
            qualityGatewayScanResult.setReliabilityRating(getValueByKey(sonarMetrics, SonarMetricDefinition.RELIABILITY_RATING));
            qualityGatewayScanResult.setVulnerabilities(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.VULNERABILITIES)));
            qualityGatewayScanResult.setNewVulnerabilities(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.NEW_VULNERABILITIES)));
            qualityGatewayScanResult.setSecurityRating(getValueByKey(sonarMetrics, SonarMetricDefinition.SECURITY_RATING));
            qualityGatewayScanResult.setLinesofCode(parseLong(getValueByKey(sonarMetrics, SonarMetricDefinition.NCLOC)));
            qualityGatewayScanResult.setCodeCoverage(parseDecimal(getValueByKey(sonarMetrics, SonarMetricDefinition.COVERAGE)));
            qualityGatewayScanResult.setNewCodeCoverage(parseDecimal(getValueByKey(sonarMetrics, SonarMetricDefinition.NEW_COVERAGE)));
            qualityGatewayScanResult.setNoofUnitTests(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.TESTS)));
            qualityGatewayScanResult.setNoofUnitTestsFailed(parseInteger(getValueByKey(sonarMetrics, SonarMetricDefinition.TEST_ERRORS)));
            qualityGatewayScanResult.setUnitTestSuccessDensity(parseDecimal(getValueByKey(sonarMetrics, SonarMetricDefinition.TEST_SUCCESS_DENSITY)));
            return Optional.of(qualityGatewayScanResult);
        }
        return Optional.empty();
    }

    private static <T> T getValueByKey(HashMap<String, Object> sonarMetrics, String key){
        if(!StringUtils.isEmpty(key)){
            Object value = sonarMetrics.containsKey(key) ? sonarMetrics.get(key) : null;
            if(Objects.nonNull(value)){
                return (T)value;
            }
        }
        return null;
    }

    private static Integer parseInteger(String value) {
        try {
            return !StringUtils.isEmpty(value) ? Integer.valueOf(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static BigDecimal parseDecimal(String value) {
        try {
            return !StringUtils.isEmpty(value) ? new BigDecimal(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Long parseLong(String value) {
        try {
            return !StringUtils.isEmpty(value) ? Long.valueOf(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static HashMap<String, Object> extractSonarValues(List<SonarMeasure> measures) {
        HashMap<String, Object> sonarMetrics = new HashMap<>();
        List<String> metricDefinitions = Arrays.asList(SonarMetricDefinition.sonarMetrics);
        metricDefinitions.forEach(metric -> {
            Object extractedValue = null;
            Optional<SonarMeasure> sonarMeasure = measures.stream()
                    .filter(m -> m.getMetric().equalsIgnoreCase(metric))
                    .findFirst();
            if (sonarMeasure.isPresent()) {
                Optional<SonarUnit> sonarUnit = sonarMeasure.get().getHistory().stream().findFirst();
                if (sonarUnit.isPresent()) {
                    extractedValue = sonarUnit.get().getValue();
                }
            }
            sonarMetrics.put(metric, extractedValue);
        });
        return sonarMetrics;
    }

    private static <T> Optional<T> getValue(List<SonarMeasure> measures, String key) {
        Optional<SonarMeasure> sonarMeasure = measures.stream()
                .filter(m -> m.getMetric().equalsIgnoreCase(key))
                .findFirst();
        if (sonarMeasure.isPresent()) {
            Optional<SonarUnit> sonarUnit = sonarMeasure.get().getHistory().stream().findFirst();
            if (sonarUnit.isPresent()) {
                return Optional.of((T) sonarUnit.get().getValue());
            }
        }
        return Optional.empty();
    }
}
