package com.ts.codemetrics.service.provider.cqprovider.sonarqube;

import lombok.Data;

@Data
public class ScanInfo {
    private String id;
    private String type;
    private String componentId;
    private String componentKey;
    private String componentName;
    private String analysisId;
    private String status;
    private String submittedAt;
    private String executedAt;
}
