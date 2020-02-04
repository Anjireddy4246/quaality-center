package com.ts.codemetrics.repository.v1;

import com.ts.codemetrics.entity.Cqconfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CQConfigRepository extends CrudRepository<Cqconfig, Long> {
    @Query(value = "SELECT CQ.* FROM CQConfig CQ " +
            " INNER JOIN Project_CQConfig PCQ ON PCQ.CQConfigId = CQ.ID " +
            " INNER JOIN Project P ON PCQ.ProjectId = P.Id" +
            " WHERE P.CIJobName = :ciJobName AND P.RowstatusId = 1 AND PCQ.RowstatusId = 1 LIMIT 1", nativeQuery = true)
    Cqconfig getCQConfigByJobName(@Param("ciJobName") String ciJobName);
}
