package com.ts.codemetrics.service.cqprovider.sonarqube;

import lombok.Data;

import java.util.Date;

@Data
public class SonarUnit {
    private Date date;
    private String value;
}
