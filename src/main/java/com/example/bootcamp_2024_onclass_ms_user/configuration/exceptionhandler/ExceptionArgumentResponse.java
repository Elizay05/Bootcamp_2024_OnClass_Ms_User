package com.example.bootcamp_2024_onclass_ms_user.configuration.exceptionhandler;

import org.springframework.validation.FieldError;

public class ExceptionArgumentResponse {

    private String field;
    private String message;

    public ExceptionArgumentResponse(FieldError fieldError) {
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }
    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
