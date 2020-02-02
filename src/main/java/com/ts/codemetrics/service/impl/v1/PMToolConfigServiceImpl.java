package com.ts.codemetrics.service.impl.v1;

import com.ts.codemetrics.entity.ProjectMgtToolConfig;
import com.ts.codemetrics.model.v1.PMToolConfigModel;
import com.ts.codemetrics.repository.v1.PMToolConfigRepository;
import com.ts.codemetrics.service.v1.PMToolConfigService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PMToolConfigServiceImpl implements PMToolConfigService {

    @Autowired
     private PMToolConfigRepository pmToolConfigRepository;

    @Override
    public Optional<PMToolConfigModel> getConfigByProjectCode(String jobName) {
        PMToolConfigModel pmToolConfigModel = new PMToolConfigModel();
        ProjectMgtToolConfig projectMgtToolConfig = pmToolConfigRepository.getConfigByProjectCode(jobName);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(projectMgtToolConfig, pmToolConfigModel);
        return Optional.of(pmToolConfigModel);
    }
}
