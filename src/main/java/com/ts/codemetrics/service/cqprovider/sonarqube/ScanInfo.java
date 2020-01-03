package com.ts.codemetrics.service.cqprovider.sonarqube;

import lombok.Data;

import java.util.Date;

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
