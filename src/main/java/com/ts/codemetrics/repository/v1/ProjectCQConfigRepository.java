package com.ts.codemetrics.repository.v1;

import com.ts.codemetrics.entity.ProjectCqconfig;
import com.ts.codemetrics.entity.ProjectMgtToolConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectCQConfigRepository extends CrudRepository<ProjectCqconfig, Long> {
    @Query(value = "Select PCQ.* FROM Project_CQConfig PCQ INNER JOIN Project P ON PCQ.ProjectId = P.Id" +
            " WHERE P.CIJobName = :ciJobName AND P.RowstatusId = 1 AND PCQ.RowstatusId = 1 LIMIT 1", nativeQuery = true)
    ProjectCqconfig getProjectCQConfigByProjectCode(@Param("ciJobName") String ciJobName);
}
