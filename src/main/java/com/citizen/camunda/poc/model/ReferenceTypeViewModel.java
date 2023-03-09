package com.citizen.camunda.poc.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ReferenceTypeViewModel {
    private String referenceType;
    private String referenceTypeDesc;
    private List<ReferenceTypeViewChildModel> values;
}
