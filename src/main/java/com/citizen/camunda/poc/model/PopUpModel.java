package com.citizen.camunda.poc.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PopUpModel {
    private Long id;
    private String name;
    private String messageBody;
    private String buttonLabel1;
    private String buttonLabel2;
}
