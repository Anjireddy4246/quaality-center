package com.ts.codemetrics.service.cqprovider.sonarqube;

import lombok.Data;

import java.util.List;

@Data
public class SonarScanResult {
    private List<SonarMeasure> measures;
}
