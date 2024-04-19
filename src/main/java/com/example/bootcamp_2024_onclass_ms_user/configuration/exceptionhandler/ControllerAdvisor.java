package com.example.bootcamp_2024_onclass_ms_user.configuration.exceptionhandler;


import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.exception.IdentificationDocumentAlreadyExistsException;
import com.example.bootcamp_2024_onclass_ms_user.configuration.Constants;
import com.example.bootcamp_2024_onclass_ms_user.domain.exception.InvalidArgumentsEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionArgumentResponse>> handlerArgumentInvalidException(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors()
                .stream()
                .map(ExceptionArgumentResponse::new)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(IdentificationDocumentAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleIdentificationDocumentAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                Constants.IDENTIFICATION_DOCUMENT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidArgumentsEmailException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidArgumentsEmailException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                Constants.ARGUMENTS_EMAIL_NOT_VALID_EXCEPTION_MESSAGE, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

}
