package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.entity.PopUp;
import com.citizen.camunda.poc.entity.ReferenceType;
import com.citizen.camunda.poc.generator.CsvFileGenerator;
import com.citizen.camunda.poc.model.PopUpModel;
import com.citizen.camunda.poc.model.ReferenceTypeModel;
import com.citizen.camunda.poc.model.ReferenceTypeSearchModel;
import com.citizen.camunda.poc.repo.ReferenceTypeRepository;
import com.citizen.camunda.poc.specification.ReferenceTypeSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReferenceTypeService implements IReferenceTypeService {
  private static final Logger logger = LoggerFactory.getLogger(ReferenceTypeService.class);

  @Autowired
  private ReferenceTypeRepository referenceTypeRepository;

  @Autowired
  private CsvFileGenerator csvFileGenerator;

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


  @Override
  public void deleteByIdIn(List<Long> ids) {
    referenceTypeRepository.deleteAllByIdInBatch(ids);
  }

  @Override
  public List<ReferenceTypeModel> search(ReferenceTypeSearchModel searchModel) {
    ReferenceTypeSpecification spec =
            new ReferenceTypeSpecification(searchModel);

    return referenceTypeRepository.findAll(spec).stream()
            .map(this::constructModelFromEntity)
            .collect(Collectors.toList());
  }

  @Override
  public void exportData(List<Long> ids, HttpServletResponse response) throws IOException {
    List<ReferenceTypeModel> referenceTypeModels = referenceTypeRepository.findAllById(ids)
            .stream()
            .map(this::constructModelFromEntity)
            .collect(Collectors.toList());
    csvFileGenerator.writeStudentsToCsv(referenceTypeModels, response.getWriter());
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
