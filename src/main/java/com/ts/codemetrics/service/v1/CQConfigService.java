package com.ts.codemetrics.service.v1;

import com.ts.codemetrics.model.v1.CQConfigModel;

import java.util.Optional;

public interface CQConfigService {
    Optional<CQConfigModel> getCQConfigByJobName(String ciJobName);
}
