package com.ts.codemetrics.service.cqprovider.sonarqube;

import com.ts.codemetrics.service.cqprovider.QualityGatewayProvider;
import com.ts.codemetrics.service.cqprovider.model.QualityGatewayScanResult;
import com.ts.codemetrics.service.cqprovider.model.ScanInfoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class SonarqubeProvider implements QualityGatewayProvider {

    private static final String TASK_URL = "api/ce/task?id=%s";
    private static final String PROJECT_METRICS = "api/measures/search_history";
    private static final String PROJECT_ANALYSIS = "api/project_analyses/search?project=%s&category=VERSION";
    private static final Logger LOGGER = LoggerFactory.getLogger(SonarqubeProvider.class);

    private WebClient getClient(String url, String uname, String pwd) {
        DefaultUriBuilderFactory builderFactory = new DefaultUriBuilderFactory(url);
        builderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        return WebClient.builder()
                .filter(ExchangeFilterFunctions.basicAuthentication(uname, pwd))
                .uriBuilderFactory(builderFactory)
                .baseUrl(url)
                .build();
    }


    @Override
    public Mono<QualityGatewayScanResult> getCodeQualityMetrics(ScanInfoRequest scanInfoRequest) {
        return getScanDetails(scanInfoRequest);

    }

    @Override
    public String providerName() {
        return "SONARQUBE";
    }

    private Mono<QualityGatewayScanResult> getSonarHistory(ScanInfoRequest scanInfoRequest, ScanInfo scanInfo){
        WebClient webClient = getClient(scanInfoRequest.getCqUrl(),
                scanInfoRequest.getUid(), scanInfoRequest.getPwd());
        Mono<QualityGatewayScanResult> result = webClient.get()
                .uri(String.format(PROJECT_ANALYSIS, scanInfo.getComponentKey()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    LOGGER.error(clientResponse.toString());
                    return Mono.empty();
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    LOGGER.error(clientResponse.toString());
                    return Mono.empty();
                }).bodyToMono(SonarAnalysisResponse.class)
                .flatMap(x->{
                  Optional<SonarAnalysis> sonarAnalysis =  x.getAnalyses().stream().filter(m->
                        m.getKey().equals(scanInfo.getAnalysisId())).findFirst();
                  if(sonarAnalysis.isPresent()){
                      scanInfo.setSubmittedAt(sonarAnalysis.get().getDate());
                  }
                    return getQualityGatewayScanResult(scanInfoRequest, scanInfo);
                });
        return result;
    }

    private Mono<QualityGatewayScanResult> getScanDetails(ScanInfoRequest scanInfoRequest) {
        WebClient webClient = getClient(scanInfoRequest.getCqUrl(),
                scanInfoRequest.getUid(), scanInfoRequest.getPwd());
        Mono<QualityGatewayScanResult> scanInfo = webClient.get().uri(String.format(TASK_URL, scanInfoRequest.getScanId()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    LOGGER.error(clientResponse.toString());
                    return Mono.empty();
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    LOGGER.error(clientResponse.toString());
                    return Mono.empty();
                }).bodyToMono(SonarTaskResult.class)
                .flatMap(x->{
                   return getSonarHistory(scanInfoRequest, x.getTask());
                });
        return scanInfo;
    }

    private Mono<QualityGatewayScanResult> getQualityGatewayScanResult(ScanInfoRequest scanInfoRequest, ScanInfo scanResponse) {
        WebClient webClient = getClient(scanInfoRequest.getCqUrl(),
                scanInfoRequest.getUid(), scanInfoRequest.getPwd());
        String metricsUrl = "metrics=ncloc,complexity,violations,code_smells,sqale_index,sqale_debt_ratio&component=USR&from=2019-12-21T05:10:26+0000";
        String metrics = String.join(",", SonarMetricDefinition.sonarMetrics);
        Mono<QualityGatewayScanResult> scanInfo = webClient.get()
                        .uri(uriBuilder -> uriBuilder.path(PROJECT_METRICS)
                        .queryParam("metrics", "ncloc,complexity,violations,code_smells,sqale_index,sqale_debt_ratio")
                        .queryParam("component", scanResponse.getComponentKey())
                        .queryParam("from", scanResponse.getSubmittedAt().replace("+","%2b"))
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    LOGGER.error(clientResponse.toString());
                    return Mono.empty();
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    LOGGER.error(clientResponse.toString());
                    return Mono.empty();
                })
                .bodyToMono(SonarScanResult.class)
                .map(x -> {
                    System.out.println(x.toString());
                    Optional<QualityGatewayScanResult> scanResult = ResponseMapper.mapSonarResponseToQualityGatewayResponse((x));
                    if(scanResult.isPresent()){
                        return scanResult.get();
                    }
                    QualityGatewayScanResult qualityGatewayScanResult = new QualityGatewayScanResult();
                    qualityGatewayScanResult.setResponse(x.toString());
                    return qualityGatewayScanResult;
                });
        return scanInfo;
    }
}
