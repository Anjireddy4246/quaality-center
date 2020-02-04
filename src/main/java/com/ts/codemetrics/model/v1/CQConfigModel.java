package com.ts.codemetrics.model.v1;

import lombok.Data;

@Data
public class CQConfigModel {
    private Long id;
    private String name;
    private String url;
    private String userId;
    private String password;
    private String accessToken;
    private String authType;
    private Integer accountId;
    private Integer cqproviderId;
}
