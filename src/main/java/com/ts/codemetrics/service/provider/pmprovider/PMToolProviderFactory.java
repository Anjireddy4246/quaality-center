package com.ts.codemetrics.service.provider.pmprovider;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PMToolProviderFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(PMToolProviderFactory.class);
    private static final Map<String, PMToolProvider> PROVIDERS = new HashMap<>();

    public PMToolProviderFactory(@Autowired List<PMToolProvider> providers){
        providers.stream().forEach(x -> PMToolProviderFactory.PROVIDERS.putIfAbsent(x.providerName(), x));
    }

    /**
     *
     * @param name
     * @return
     */
    public PMToolProvider getProvider(String name) {
        if (PMToolProviderFactory.PROVIDERS.containsKey(name)) {
            return PROVIDERS.get(name);
        }
        LOGGER.warn("No Provider configured with the name"+ name);
        return null;
    }
}
