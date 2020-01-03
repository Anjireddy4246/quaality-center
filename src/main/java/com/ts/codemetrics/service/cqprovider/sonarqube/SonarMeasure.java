package com.ts.codemetrics.service.cqprovider.sonarqube;

import lombok.Data;

import java.util.List;

@Data
public class SonarMeasure {
    private String metric;
    private List<SonarUnit> history;
}
