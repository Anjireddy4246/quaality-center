package com.ts.codemetrics.service.impl.v1.mapper;

import com.ts.codemetrics.entity.ReleaseItem;
import com.ts.codemetrics.model.v1.ReleaseItemModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Date;

public class ReleaseItemModelToEntity {
    public static ReleaseItem mapModelToEntity(ReleaseItemModel releaseItemModel){
        ReleaseItem releaseItem = new ReleaseItem();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(releaseItemModel, releaseItem);
        releaseItem.setRowStatusId(1);
        releaseItem.setLastSyncedOn(new Date());
        if(releaseItemModel.getSeverity() != null){
            releaseItem.setSeverity(releaseItemModel.getSeverity().value());
        }
        if(releaseItemModel.getWorkItemType() != null) {
            releaseItem.setItemType(releaseItemModel.getWorkItemType().value());
        }
        return releaseItem;
    }
}
