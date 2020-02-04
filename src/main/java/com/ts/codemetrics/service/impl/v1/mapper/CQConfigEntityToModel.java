package com.ts.codemetrics.service.impl.v1.mapper;

import com.ts.codemetrics.entity.Cqconfig;
import com.ts.codemetrics.model.v1.CQConfigModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Objects;
import java.util.Optional;

public class CQConfigEntityToModel {
    public static Optional<CQConfigModel> mapEntityToModel(Cqconfig cqConfig){
        CQConfigModel cqConfigModel = new CQConfigModel();
        if(Objects.nonNull(cqConfig)){
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(cqConfig, cqConfigModel);
            return Optional.ofNullable(cqConfigModel);
        }
        return Optional.empty();
    }
}
