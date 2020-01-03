package com.ts.codemetrics.service.cqprovider.sonarqube;

import lombok.Data;

@Data
public class SonarAnalysis {
    private String key;
    private String date;
    private String revision;
    private String projectVersion;
}
