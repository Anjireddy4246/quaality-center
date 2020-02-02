package com.ts.codemetrics.service.v1;

import com.ts.codemetrics.model.v1.ReleaseItemModel;

import java.util.Optional;

public interface ReleaseItemService {
    Optional<ReleaseItemModel> create(ReleaseItemModel releaseItemModel);
    Optional<ReleaseItemModel> update(Long id, ReleaseItemModel releaseItemModel);
    Optional<Boolean> delete(String externalId);

    Optional<ReleaseItemModel> findByWorkItemCode(String itemCode);
}
