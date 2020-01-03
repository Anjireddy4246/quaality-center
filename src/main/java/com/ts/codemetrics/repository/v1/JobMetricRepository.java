package com.ts.codemetrics.repository.v1;

import com.ts.codemetrics.entity.JobMetric;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobMetricRepository extends CrudRepository<JobMetric, Long> {
}
