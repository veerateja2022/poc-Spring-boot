package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.model.EmailModel;
import com.citizen.camunda.poc.model.EmployeeDetailsModel;
import com.citizen.camunda.poc.model.PopUpModel;
import com.citizen.camunda.poc.model.User;
import com.citizen.camunda.poc.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IPopUpService {
    List<PopUpModel> getAll();
    PopUpModel getPopUpModelById(Long id);
    PopUpModel save(PopUpModel popUpModel);
    void deleteById(Long id);
}
