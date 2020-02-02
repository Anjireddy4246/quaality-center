package com.ts.codemetrics.service.impl.v1.mapper;

import com.ts.codemetrics.entity.ReleaseItem;
import com.ts.codemetrics.model.v1.ReleaseItemModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ReleaseItemEntityToModel {
    public static ReleaseItemModel mapReleaseItemEntityToModel(ReleaseItem releaseItem){
            ReleaseItemModel releaseItemModel = new ReleaseItemModel();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(releaseItem, releaseItemModel);
            return releaseItemModel;
    }
}
