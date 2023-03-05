package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.model.ReferenceTypeModel;
import com.citizen.camunda.poc.model.ReferenceTypeSearchModel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IReferenceTypeService {
    List<ReferenceTypeModel> getAll();
    ReferenceTypeModel getById(Long id);
    ReferenceTypeModel save(ReferenceTypeModel referenceTypeModel);
    void deleteById(Long id);
    void deleteByIdIn(List<Long> ids);
    List<ReferenceTypeModel> search(ReferenceTypeSearchModel searchModel);
    void exportData(List<Long> ids, HttpServletResponse response) throws IOException;

}
