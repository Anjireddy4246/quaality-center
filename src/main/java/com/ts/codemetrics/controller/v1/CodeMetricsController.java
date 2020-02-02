package com.ts.codemetrics.controller.v1;


import com.ts.codemetrics.service.provider.cqprovider.CQProviderFactory;
import com.ts.codemetrics.service.provider.cqprovider.QualityGatewayProvider;
import com.ts.codemetrics.service.provider.cqprovider.model.QualityGatewayScanResult;
import com.ts.codemetrics.service.provider.cqprovider.model.ScanInfoRequest;
import com.ts.codemetrics.model.v1.CodeMetricModel;
import com.ts.codemetrics.service.impl.v1.JobMetricServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("v1/code-metrics")
public class CodeMetricsController {
    private static List<CodeMetricModel> codeMetricModes = new ArrayList<>();

    @Autowired
    private JobMetricServiceImpl jobMetricServiceImpl;

    @Autowired
    private CQProviderFactory cqProviderFactory;

    @PostMapping()
    public ResponseEntity<Mono<CodeMetricModel>> save(@RequestBody CodeMetricModel codeMetricModel) {
        Optional<CodeMetricModel> model = jobMetricServiceImpl.create(codeMetricModel);
        if(model.isPresent()){
            return  new ResponseEntity<>(Mono.justOrEmpty(model.get()), HttpStatus.CREATED);
        }
        return ResponseEntity.ok(Mono.just(codeMetricModel));
    }

    @GetMapping()
    public ResponseEntity<Mono<List<CodeMetricModel>>> getAll() {
        return ResponseEntity.ok(Mono.justOrEmpty(codeMetricModes));
    }

    @GetMapping("cq")
    public ResponseEntity<Mono<QualityGatewayScanResult>> getMetrics(){
        QualityGatewayProvider qualityGatewayProvider = cqProviderFactory.getProvider("SONARQUBE");
        ScanInfoRequest scanInfoRequest = new ScanInfoRequest();
        scanInfoRequest.setCqUrl("https://codequality.technosoftcorp.net/");
        scanInfoRequest.setUid("admin");
        scanInfoRequest.setPwd("bitnami");
        scanInfoRequest.setScanId("AW9kTBsngs_jXp1CnD9W");
        return ResponseEntity.ok(qualityGatewayProvider.getCodeQualityMetrics(scanInfoRequest));
    }
}
