package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.entity.PopUp;
import com.citizen.camunda.poc.entity.ReferenceType;
import com.citizen.camunda.poc.generator.CsvFileGenerator;
import com.citizen.camunda.poc.model.PopUpModel;
import com.citizen.camunda.poc.model.ReferenceTypeModel;
import com.citizen.camunda.poc.model.ReferenceTypeSearchModel;
import com.citizen.camunda.poc.model.ReferenceTypeViewChildModel;
import com.citizen.camunda.poc.model.ReferenceTypeViewModel;
import com.citizen.camunda.poc.repo.ReferenceTypeRepository;
import com.citizen.camunda.poc.specification.ReferenceTypeSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReferenceTypeService implements IReferenceTypeService {
  private static final Logger logger = LoggerFactory.getLogger(ReferenceTypeService.class);

  @Autowired
  private ReferenceTypeRepository referenceTypeRepository;

  @Autowired
  private CsvFileGenerator csvFileGenerator;

  @Override
  public List<ReferenceTypeViewModel> getAll() {
    return constructReferenceTypeViewModel(referenceTypeRepository.findAll());
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
  public void deleteById(ReferenceTypeViewModel searchModel) {
    referenceTypeRepository.deleteByReferenceTypeAndReferenceTypeDesc(searchModel.getReferenceType(), searchModel.getReferenceTypeDesc());
  }


  @Override
  public void deleteByIdIn(List<Long> ids) {
    referenceTypeRepository.deleteAllByIdInBatch(ids);
  }

  @Override
  public List<ReferenceTypeViewModel> search(ReferenceTypeSearchModel searchModel) {
    ReferenceTypeSpecification spec =
            new ReferenceTypeSpecification(searchModel);

    return constructReferenceTypeViewModel(referenceTypeRepository.findAll(spec));
  }

  @Override
  public void exportData(List<Long> ids, HttpServletResponse response) throws IOException {
    List<ReferenceTypeModel> referenceTypeModels = referenceTypeRepository.findAllById(ids)
            .stream()
            .map(this::constructModelFromEntity)
            .collect(Collectors.toList());
    csvFileGenerator.writeStudentsToCsv(referenceTypeModels, response.getWriter());
  }

  @Override
  public List<ReferenceTypeViewModel> findByTypeAndDesc(ReferenceTypeViewModel searchModel) {
    return constructReferenceTypeViewModel(referenceTypeRepository.findByReferenceTypeAndReferenceTypeDesc(searchModel.getReferenceType(), searchModel.getReferenceTypeDesc()));
  }

  private ReferenceTypeModel constructModelFromEntity(ReferenceType referenceType) {
    ReferenceTypeModel referenceTypeModel = new ReferenceTypeModel();
    BeanUtils.copyProperties(referenceType, referenceTypeModel);
    return referenceTypeModel;
  }

  private ReferenceTypeViewChildModel constructChildModelFromEntity(ReferenceType referenceType) {
    ReferenceTypeViewChildModel referenceTypeModel = new ReferenceTypeViewChildModel();
    BeanUtils.copyProperties(referenceType, referenceTypeModel);
    return referenceTypeModel;
  }

  private List<ReferenceTypeViewModel> constructReferenceTypeViewModel(List<ReferenceType> referenceTypes) {
    Function<ReferenceType, List<Object>> compositeKey = referenceType ->
            Arrays.<Object>asList( referenceType.getReferenceType(), referenceType.getReferenceTypeDesc());
    Map<Object, List<ReferenceType>> map =
            referenceTypes.stream().collect(Collectors.groupingBy(compositeKey, Collectors.toList()));
    List<ReferenceTypeViewModel> referenceTypeViewModels = new ArrayList<>();
    for (Map.Entry<Object, List<ReferenceType>> entry : map.entrySet()) {
      List<ReferenceType> referenceTypeList = entry.getValue();
      List<Object> objects = (List<Object>) entry.getKey();
      ReferenceTypeViewModel referenceTypeViewModel = new ReferenceTypeViewModel();
      referenceTypeViewModel.setReferenceType(objects.get(0)+"");
      referenceTypeViewModel.setReferenceTypeDesc(objects.get(1)+"");
      List<ReferenceTypeViewChildModel> referenceTypeViewChildModels = referenceTypeList.stream()
                      .map(this::constructChildModelFromEntity).collect(Collectors.toList());
      referenceTypeViewModel.setValues(referenceTypeViewChildModels);
      referenceTypeViewModels.add(referenceTypeViewModel);
    }
    return referenceTypeViewModels;
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
