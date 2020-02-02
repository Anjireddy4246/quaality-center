package com.ts.codemetrics.model.v1;

import lombok.Data;

@Data
public class PMToolConfigModel {
    private Long id;
    private String name;
    private String url;
    private String loginId;
    private String password;
    private String accessToken;
}
