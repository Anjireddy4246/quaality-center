package com.ts.codemetrics.service.impl.v1;

import com.ts.codemetrics.entity.Account;
import com.ts.codemetrics.entity.CodeQualityMetric;
import com.ts.codemetrics.entity.JobMetric;
import com.ts.codemetrics.model.v1.CodeMetricModel;
import com.ts.codemetrics.model.v1.PMToolConfigModel;
import com.ts.codemetrics.model.v1.ReleaseItemModel;
import com.ts.codemetrics.repository.v1.CodeQualityMetricRepository;
import com.ts.codemetrics.repository.v1.JobMetricRepository;
import com.ts.codemetrics.service.provider.cqprovider.CQProviderFactory;
import com.ts.codemetrics.service.provider.cqprovider.QualityGatewayProvider;
import com.ts.codemetrics.service.provider.cqprovider.model.ScanInfoRequest;
import com.ts.codemetrics.service.provider.pmprovider.PMToolProvider;
import com.ts.codemetrics.service.provider.pmprovider.PMToolProviderFactory;
import com.ts.codemetrics.service.v1.JobMetricService;
import com.ts.codemetrics.service.v1.PMToolConfigService;
import com.ts.codemetrics.service.v1.ReleaseItemService;
import org.codehaus.plexus.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class JobMetricServiceImpl implements JobMetricService {

    @Autowired
    private JobMetricRepository jobMetricRepository;

    @Autowired
    private CQProviderFactory cqProviderFactory;

    @Autowired
    private CodeQualityMetricRepository codeQualityMetricRepository;

    @Autowired
    private PMToolConfigService pmToolConfigService;

    @Autowired
    private ReleaseItemService releaseItemService;

    @Autowired
    private PMToolProviderFactory pmToolProviderFactory;

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
        jobMetric.setAccount(new Account() {{
            setId(1);
        }});
        jobMetric = jobMetricRepository.save(jobMetric);
        codeMetricModel.setId(jobMetric.getId());
        getQualityGatewayScanDetails(codeMetricModel);
        syncCheckInItemDetails(codeMetricModel);
        return Optional.of(codeMetricModel);
    }

    private void triggerCodeQualityJob(CodeMetricModel codeMetricModel) {
        QualityGatewayProvider qualityGatewayProvider = cqProviderFactory.getProvider("SONARQUBE");
        if (qualityGatewayProvider != null) {
            qualityGatewayProvider.getCodeQualityMetrics(constructScanInfoRequest(codeMetricModel))
                    .subscribe(m -> {
                        CodeQualityMetric codeQualityMetric = new CodeQualityMetric();
                        ModelMapper modelMapper = new ModelMapper();
                        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                        modelMapper.map(m, codeQualityMetric);
                        codeQualityMetric.setJobMetric(new JobMetric() {{
                            setId(codeMetricModel.getId());
                        }});
                        codeQualityMetric.setCreatedDate(new Date());
                        codeQualityMetricRepository.save(codeQualityMetric);
                    });
        }
    }

    private void getQualityGatewayScanDetails(CodeMetricModel codeMetricModel) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
                triggerCodeQualityJob(codeMetricModel));
    }

    private void syncCheckInItemDetails(CodeMetricModel codeMetricModel) {
        if (Objects.nonNull(codeMetricModel) && StringUtils.isNotBlank(codeMetricModel.getCheckInComments())) {
            String comment = codeMetricModel.getCheckInComments();
            String workItemId = "";
            String[] commentsDetails = comment.split(" ");
            if (commentsDetails != null && commentsDetails.length > 0) {
                workItemId = commentsDetails[0];
            }
            if (StringUtils.isNotEmpty(workItemId)) {
                Optional<PMToolConfigModel> pmToolConfigModel =
                        pmToolConfigService.getConfigByProjectCode(codeMetricModel.getJobName());
                if (pmToolConfigModel.isPresent()) {
                    PMToolProvider pmToolProvider = pmToolProviderFactory.getProvider(pmToolConfigModel.get().getName());
                    ReleaseItemModel releaseItemModel =
                            pmToolProvider.getIssue(pmToolConfigModel.get(), workItemId);
                    releaseItemService.create(releaseItemModel);
                }
            }
        }
    }


    private ScanInfoRequest constructScanInfoRequest(CodeMetricModel codeMetricModel) {
        ScanInfoRequest scanInfoRequest = new ScanInfoRequest();
        scanInfoRequest.setCqUrl("https://codequality.technosoftcorp.net/");
        scanInfoRequest.setUid("admin");
        scanInfoRequest.setPwd("bitnami");
        scanInfoRequest.setScanId(codeMetricModel.getCqTaskId());
        return scanInfoRequest;
    }
}
