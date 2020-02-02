package com.ts.codemetrics.repository.v1;

import com.ts.codemetrics.entity.ProjectMgtToolConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface PMToolConfigRepository extends CrudRepository<ProjectMgtToolConfig, Long> {
    @Query(value = "Select * FROM ProjectMgtToolConfig PMC INNER JOIN Project P ON PMC.ProjectId = P.Id" +
            " WHERE P.CIJobName = :ciJobName AND P.RowstatusId = 1 AND PMC.RowstatusId = 1 LIMIT 1", nativeQuery = true)
    ProjectMgtToolConfig getConfigByProjectCode(@Param("ciJobName") String ciJobName);
}
