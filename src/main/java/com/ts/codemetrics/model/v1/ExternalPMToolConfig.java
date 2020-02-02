package com.ts.codemetrics.model.v1;

import lombok.Data;

@Data
public class ExternalPMToolConfig {
    private String projectCode;
    private String url;
    private String userName;
    private String password;
    private String accessToken;
}
