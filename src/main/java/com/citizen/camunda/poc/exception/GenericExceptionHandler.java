package com.citizen.camunda.poc.exception;

import com.citizen.camunda.poc.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ErrorResponse handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException exception) {
        return ErrorResponse.builder()
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .error("acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE)
                .message(exception.getMessage())
                .build();
    }
}
