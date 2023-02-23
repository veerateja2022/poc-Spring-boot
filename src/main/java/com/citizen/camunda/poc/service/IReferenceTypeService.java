package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.model.ReferenceTypeModel;

import java.util.List;

public interface IReferenceTypeService {
    List<ReferenceTypeModel> getAll();
    ReferenceTypeModel getById(Long id);
    ReferenceTypeModel save(ReferenceTypeModel referenceTypeModel);
    void deleteById(Long id);
}
