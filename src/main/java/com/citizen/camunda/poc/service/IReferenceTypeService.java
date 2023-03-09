package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.model.ReferenceTypeModel;
import com.citizen.camunda.poc.model.ReferenceTypeSearchModel;
import com.citizen.camunda.poc.model.ReferenceTypeViewModel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IReferenceTypeService {
    List<ReferenceTypeViewModel> getAll();
    ReferenceTypeModel getById(Long id);
    ReferenceTypeModel save(ReferenceTypeModel referenceTypeModel);
    void deleteById(ReferenceTypeViewModel searchModel);
    void deleteByIdIn(List<Long> ids);
    List<ReferenceTypeViewModel> search(ReferenceTypeSearchModel searchModel);
    void exportData(List<Long> ids, HttpServletResponse response) throws IOException;
    List<ReferenceTypeViewModel> findByTypeAndDesc(ReferenceTypeViewModel searchModel);
}
