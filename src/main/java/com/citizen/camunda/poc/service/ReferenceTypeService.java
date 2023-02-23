package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.entity.PopUp;
import com.citizen.camunda.poc.entity.ReferenceType;
import com.citizen.camunda.poc.model.PopUpModel;
import com.citizen.camunda.poc.model.ReferenceTypeModel;
import com.citizen.camunda.poc.repo.ReferenceTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReferenceTypeService implements IReferenceTypeService {
  private static final Logger logger = LoggerFactory.getLogger(ReferenceTypeService.class);

  @Autowired
  private ReferenceTypeRepository referenceTypeRepository;

  @Override
  public List<ReferenceTypeModel> getAll() {
    return referenceTypeRepository.findAll()
            .stream()
            .map(this::constructModelFromEntity)
            .collect(Collectors.toList());
  }

  @Override
  public ReferenceTypeModel getById(Long id) {
    Optional<ReferenceType> optionalReferenceType = referenceTypeRepository.findById(id);
    if (optionalReferenceType.isPresent()) {
      return constructModelFromEntity(optionalReferenceType.get());
    }
    return null;
  }

  @Override
  public ReferenceTypeModel save(ReferenceTypeModel referenceTypeModel) {
    referenceTypeRepository.save(constructEntityFromModel(referenceTypeModel));
    return referenceTypeModel;
  }

  @Override
  public void deleteById(Long id) {
    referenceTypeRepository.deleteById(id);
  }


  private ReferenceTypeModel constructModelFromEntity(ReferenceType referenceType) {
    ReferenceTypeModel referenceTypeModel = new ReferenceTypeModel();
    BeanUtils.copyProperties(referenceType, referenceTypeModel);
    return referenceTypeModel;
  }

  private ReferenceType constructEntityFromModel(ReferenceTypeModel referenceTypeModel) {
    ReferenceType referenceType = new ReferenceType();
    if (referenceTypeModel.getId() != null) {
      referenceType.setId(referenceTypeModel.getId());
    }
    referenceType.setReferenceType(referenceTypeModel.getReferenceType());
    referenceType.setReferenceTypeDesc(referenceTypeModel.getReferenceTypeDesc());
    referenceType.setReferenceValue(referenceTypeModel.getReferenceValue());
    referenceType.setReferenceValueDesc(referenceTypeModel.getReferenceTypeDesc());
    referenceType.setEffectiveStartDate(referenceTypeModel.getEffectiveStartDate());
    referenceType.setEffectiveEndDate(referenceTypeModel.getEffectiveEndDate());
    return referenceType;
  }

}
