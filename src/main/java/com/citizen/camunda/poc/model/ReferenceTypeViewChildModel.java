package com.citizen.camunda.poc.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReferenceTypeViewChildModel {

    private String referenceValue;
    private String referenceValueDesc;
    private String effectiveStartDate;
    private String effectiveEndDate;
}
