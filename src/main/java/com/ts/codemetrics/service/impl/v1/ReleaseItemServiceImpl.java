package com.ts.codemetrics.service.impl.v1;

import com.ts.codemetrics.entity.ReleaseItem;
import com.ts.codemetrics.model.v1.ReleaseItemModel;
import com.ts.codemetrics.model.v1.ValidationModel;
import com.ts.codemetrics.repository.v1.ReleaseItemRepository;
import com.ts.codemetrics.service.impl.v1.mapper.ReleaseItemEntityToModel;
import com.ts.codemetrics.service.impl.v1.mapper.ReleaseItemModelToEntity;
import com.ts.codemetrics.service.v1.ReleaseItemService;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("ReleaseServiceV1")
public class ReleaseItemServiceImpl implements ReleaseItemService {

    @Autowired
    private ReleaseItemRepository releaseItemRepository;

    @Override
    public Optional<ReleaseItemModel> create(ReleaseItemModel releaseItemModel) {
        if(Objects.nonNull(releaseItemModel) && StringUtils.isNotBlank(releaseItemModel.getItemCode())){
            Optional<ReleaseItemModel> dbReleaseItemModel = findByWorkItemCode(releaseItemModel.getItemCode());
            return dbReleaseItemModel.isPresent() ?
                    update(dbReleaseItemModel.get().getId(), releaseItemModel) : save(releaseItemModel);
        }
        else{
            releaseItemModel.setSuccess(false);
            List<ValidationModel> validations = new ArrayList<>();
            ValidationModel validationModel = new ValidationModel();
            validationModel.setPropertyName("GlobalValidations - DATA");
            validationModel.setValidationMessage("Data cannot be empty");
            validations.add(validationModel);
            releaseItemModel.setValidations(validations);
            return Optional.of(releaseItemModel);
        }

    }

    @Override
    public Optional<ReleaseItemModel> update(Long id, ReleaseItemModel releaseItemModel) {
        releaseItemModel.setId(id);
       return save(releaseItemModel);
    }

    private Optional<ReleaseItemModel> save(ReleaseItemModel releaseItemModel){
        ReleaseItem releaseItem = ReleaseItemModelToEntity.mapModelToEntity(releaseItemModel);
        releaseItem = releaseItemRepository.save(releaseItem);
        releaseItemModel = ReleaseItemEntityToModel.mapReleaseItemEntityToModel(releaseItem);
        return Optional.of(releaseItemModel);
    }

    @Override
    public Optional<Boolean> delete(String externalId) {
        return Optional.empty();
    }

    @Override
    public Optional<ReleaseItemModel> findByWorkItemCode(String itemCode) {
        ReleaseItem releaseItem = releaseItemRepository.findReleaseItemByWorkItemCode(itemCode);
        if(Objects.nonNull(releaseItem)){
            ReleaseItemModel releaseItemModel = ReleaseItemEntityToModel.mapReleaseItemEntityToModel(releaseItem);
            return Optional.of(releaseItemModel);
        }
        return Optional.empty();
    }
}
