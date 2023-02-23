package com.citizen.camunda.poc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "reference_type")
public class ReferenceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference_type")
    private String referenceType;

    @Column(name = "reference_type_desc")
    private String referenceTypeDesc;

    @Column(name = "reference_value")
    private String referenceValue;

    @Column(name = "reference_value_desc")
    private String referenceValueDesc;

    @Column(name = "effective_start_date")
    private String effectiveStartDate;

    @Column(name = "effective_end_date")
    private String effectiveEndDate;

}
