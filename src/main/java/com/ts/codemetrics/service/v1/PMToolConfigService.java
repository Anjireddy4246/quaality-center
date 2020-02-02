package com.ts.codemetrics.service.v1;


import com.ts.codemetrics.model.v1.PMToolConfigModel;

import java.util.Optional;

public interface PMToolConfigService {
    Optional<PMToolConfigModel> getConfigByProjectCode(String jobName);
}
