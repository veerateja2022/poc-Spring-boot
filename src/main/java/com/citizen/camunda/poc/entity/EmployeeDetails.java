package com.citizen.camunda.poc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeDetails {
    private static final long serialVersionUID = -6313603058414149656L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "APPLICATION_ID")
    private String applicationId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "NPI")
    private String npi;

    @Column(name = "APPLICATION")
    private String application;

    @Column(name = "COMPLETE")
    private String complete;

    @Column(name = "LAST_UPDATE")
    private String lastUpdate;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "ACTIONS")
    private String actions;

}
