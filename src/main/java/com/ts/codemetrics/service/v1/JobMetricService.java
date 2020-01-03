package com.ts.codemetrics.service.v1;

import com.ts.codemetrics.model.v1.CodeMetricModel;

import java.util.Optional;

public interface JobMetricService {
    Optional<CodeMetricModel> getById(Long id);
    Optional<CodeMetricModel> getByLoginId(String loginId);
    Optional<CodeMetricModel> create(CodeMetricModel user);
}
