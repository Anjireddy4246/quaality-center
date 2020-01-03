package com.ts.codemetrics.service.cqprovider;
import com.ts.codemetrics.service.cqprovider.model.QualityGatewayScanResult;
import com.ts.codemetrics.service.cqprovider.model.ScanInfoRequest;
import reactor.core.publisher.Mono;

public interface QualityGatewayProvider {
    Mono<QualityGatewayScanResult> getCodeQualityMetrics(ScanInfoRequest scanInfoRequest);

    String providerName();
}
