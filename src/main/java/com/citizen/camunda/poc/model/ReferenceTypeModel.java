package com.citizen.camunda.poc.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

@Data
@ToString
public class ReferenceTypeModel {
    private Long id;
    private String referenceType;
    private String referenceTypeDesc;
    private String referenceValue;
    private String referenceValueDesc;
    private String effectiveStartDate;
    private String effectiveEndDate;
}
