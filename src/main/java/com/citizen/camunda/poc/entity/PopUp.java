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
@Table(name = "pop_up")
public class PopUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "message_body")
    private String messageBody;

    @Column(name = "button_label_1")
    private String buttonLabel1;

    @Column(name = "button_label_2")
    private String buttonLabel2;

}
