package com.ts.codemetrics.service.cqprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CQProviderFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CQProviderFactory.class);
    private static final Map<String, QualityGatewayProvider> GATEWAY_PROVIDERS = new HashMap<>();

    public CQProviderFactory(@Autowired List<QualityGatewayProvider> providers){
        providers.stream().forEach(x -> CQProviderFactory.GATEWAY_PROVIDERS.putIfAbsent(x.providerName(), x));
    }

    public QualityGatewayProvider getProvider(String name) {
        if (CQProviderFactory.GATEWAY_PROVIDERS.containsKey(name)) {
            return GATEWAY_PROVIDERS.get(name);
        }
        LOGGER.warn("No Provider configured with the name"+ name);
        return null;
    }

}
