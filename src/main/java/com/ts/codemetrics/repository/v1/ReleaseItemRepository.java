package com.ts.codemetrics.repository.v1;

import com.ts.codemetrics.entity.ReleaseItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseItemRepository extends CrudRepository<ReleaseItem, Long> {
    @Query(value = "SELECT * FROM ReleaseItem WHERE ItemCode = :workItemCode AND RowStatusId = 1 LIMIT 1", nativeQuery = true)
    ReleaseItem findReleaseItemByWorkItemCode(@Param("workItemCode") String workItemCode);
}
