package com.citizen.camunda.poc.model;

import lombok.Data;

import javax.persistence.*;

@Data
public class EmployeeDetailsModel {
    private long id;

    private String applicationId;

    private String status;

    private String name;

    private String type;

    private String npi;

    private String application;

    private String complete;

    private String lastUpdate;

    private String owner;

    private String actions;

}
