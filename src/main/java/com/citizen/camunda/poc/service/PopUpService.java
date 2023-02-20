package com.citizen.camunda.poc.service;

import com.citizen.camunda.poc.entity.EmployeeDetails;
import com.citizen.camunda.poc.entity.PopUp;
import com.citizen.camunda.poc.model.EmailModel;
import com.citizen.camunda.poc.model.EmployeeDetailsModel;
import com.citizen.camunda.poc.model.PopUpModel;
import com.citizen.camunda.poc.model.User;
import com.citizen.camunda.poc.model.UserModel;
import com.citizen.camunda.poc.repo.EmployeeRepository;
import com.citizen.camunda.poc.repo.PopUpRepository;
import com.citizen.camunda.poc.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class PopUpService implements IPopUpService {
  private static final Logger logger = LoggerFactory.getLogger(PopUpService.class);

  @Autowired
  private PopUpRepository popUpRepository;


  @Override
  public List<PopUpModel> getAll() {
    return popUpRepository.findAll().stream().map(this::constructModelFromEntity).collect(Collectors.toList());
  }

  @Override
  public PopUpModel getPopUpModelById(Long id) {
    Optional<PopUp> popUpModelOptional = popUpRepository.findById(id);
    if(popUpModelOptional.isPresent()) {
      return constructModelFromEntity(popUpModelOptional.get());
    }
    return new PopUpModel();
  }

  @Override
  public PopUpModel save(PopUpModel popUpModel) {
    PopUp popUp = constructEntityFromModel(popUpModel);
    popUpRepository.save(popUp);
    return popUpModel;
  }

  @Override
  public void deleteById(Long id) {
    popUpRepository.deleteById(id);
  }

  private PopUpModel constructModelFromEntity(PopUp popUp) {
    PopUpModel popUpModel = new PopUpModel();
    BeanUtils.copyProperties(popUp, popUpModel);
    return popUpModel;
  }

  private PopUp constructEntityFromModel(PopUpModel popUpModel) {
    PopUp popUp = new PopUp();
    if (popUpModel.getId() != null) {
      popUp.setId(popUpModel.getId());
    }
    popUp.setName(popUpModel.getName());
    popUp.setMessageBody(popUpModel.getMessageBody());
    popUp.setButtonLabel1(popUpModel.getButtonLabel1());
    popUp.setButtonLabel2(popUpModel.getButtonLabel2());
    return popUp;
  }

}
