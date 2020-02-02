package com.ts.codemetrics.service.provider.cqprovider.model;

import lombok.Data;

@Data
public class ScanInfoRequest {
    private String scanId;
    private String uid;
    private String pwd;
    private String cqUrl;
}
