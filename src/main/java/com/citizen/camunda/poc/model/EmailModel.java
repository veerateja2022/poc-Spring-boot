package com.citizen.camunda.poc.model;

import lombok.Data;

@Data
public class EmailModel {
    private String to;
    private String from;
    private String subject;
    private String content;
}
