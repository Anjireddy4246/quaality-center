package com.ts.codemetrics.service.provider.cqprovider.sonarqube;

import lombok.Data;

import java.util.Date;

@Data
public class SonarUnit {
    private Date date;
    private String value;
}
