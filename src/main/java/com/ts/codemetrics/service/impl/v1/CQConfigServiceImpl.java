package com.ts.codemetrics.service.impl.v1;

import com.ts.codemetrics.entity.Cqconfig;
import com.ts.codemetrics.model.v1.CQConfigModel;
import com.ts.codemetrics.repository.v1.CQConfigRepository;
import com.ts.codemetrics.service.impl.v1.mapper.CQConfigEntityToModel;
import com.ts.codemetrics.service.v1.CQConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("CQConfigServiceImplV1")
public class CQConfigServiceImpl implements CQConfigService {

    @Autowired
    private CQConfigRepository _cqConfigRepository;

    @Override
    public Optional<CQConfigModel> getCQConfigByJobName(String ciJobName) {
        Cqconfig cqConfig = _cqConfigRepository.getCQConfigByJobName(ciJobName);
        return CQConfigEntityToModel.mapEntityToModel(cqConfig);
    }
}
