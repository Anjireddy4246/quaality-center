package com.ts.codemetrics.service.impl.v1;

import com.ts.codemetrics.entity.Account;
import com.ts.codemetrics.entity.CodeQualityMetric;
import com.ts.codemetrics.entity.JobMetric;
import com.ts.codemetrics.model.v1.CodeMetricModel;
import com.ts.codemetrics.repository.v1.CodeQualityMetricRepository;
import com.ts.codemetrics.repository.v1.JobMetricRepository;
import com.ts.codemetrics.service.cqprovider.CQProviderFactory;
import com.ts.codemetrics.service.cqprovider.QualityGatewayProvider;
import com.ts.codemetrics.service.cqprovider.model.ScanInfoRequest;
import com.ts.codemetrics.service.v1.JobMetricService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class JobMetricServiceImpl implements JobMetricService {

    @Autowired
    private JobMetricRepository jobMetricRepository;

    @Autowired
    private CQProviderFactory cqProviderFactory;

    @Autowired
    private CodeQualityMetricRepository codeQualityMetricRepository;

    @Override
    public Optional<CodeMetricModel> getById(Long Id) {
        return Optional.empty();
    }

    @Override
    public Optional<CodeMetricModel> getByLoginId(String loginId) {
        return Optional.empty();
    }

    @Override
    public Optional<CodeMetricModel> create(CodeMetricModel codeMetricModel) {
        JobMetric jobMetric = new JobMetric();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(codeMetricModel, jobMetric);
        jobMetric.setAccount(new Account(){{setId(1); }});
        jobMetric = jobMetricRepository.save(jobMetric);
        codeMetricModel.setId(jobMetric.getId());
        getQualityGatewayScanDetails(codeMetricModel);
        return Optional.of(codeMetricModel);
    }

    private void triggerCodeQualityJob(CodeMetricModel codeMetricModel){
        QualityGatewayProvider qualityGatewayProvider = cqProviderFactory.getProvider("SONARQUBE");
        if(qualityGatewayProvider != null){
            qualityGatewayProvider.getCodeQualityMetrics(constructScanInfoRequest(codeMetricModel))
                    .subscribe(m->{
                        CodeQualityMetric codeQualityMetric = new CodeQualityMetric();
                        ModelMapper modelMapper = new ModelMapper();
                        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                        modelMapper.map(m, codeQualityMetric);
                        codeQualityMetric.setJobMetric(new JobMetric(){{setId(codeMetricModel.getId());}});
                        codeQualityMetricRepository.save(codeQualityMetric);
                    });
        }
    }

    private void getQualityGatewayScanDetails(CodeMetricModel codeMetricModel) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                triggerCodeQualityJob(codeMetricModel);
        });
    }

    private ScanInfoRequest constructScanInfoRequest(CodeMetricModel codeMetricModel){
        ScanInfoRequest scanInfoRequest = new ScanInfoRequest();
        scanInfoRequest.setCqUrl("https://codequality.technosoftcorp.net/");
        scanInfoRequest.setUid("admin");
        scanInfoRequest.setPwd("bitnami");
        scanInfoRequest.setScanId(codeMetricModel.getCqTaskId());
        return scanInfoRequest;
    }
}
