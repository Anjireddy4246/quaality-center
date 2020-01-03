package com.ts.codemetrics.service.cqprovider.sonarqube;

import lombok.Data;

import java.util.List;

@Data
public class SonarAnalysisResponse {
private List<SonarAnalysis> analyses;
}
