package com.ts.codemetrics.repository.v1;

import com.ts.codemetrics.entity.CodeQualityMetric;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeQualityMetricRepository extends CrudRepository<CodeQualityMetric, Long> {
}
