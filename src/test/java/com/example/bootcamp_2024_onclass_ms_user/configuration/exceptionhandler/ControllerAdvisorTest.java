package com.example.bootcamp_2024_onclass_ms_user.configuration.exceptionhandler;

import com.example.bootcamp_2024_onclass_ms_user.configuration.Constants;
import com.example.bootcamp_2024_onclass_ms_user.domain.exception.InvalidArgumentsEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerAdvisorTest {

    @Test
    @DisplayName("Handle Argument Invalid Exception")
    void testHandleArgumentInvalidException() {

        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        BindingResult bindingResult = mock(BindingResult.class);

        List<FieldError> fieldErrors = List.of(
                new FieldError("objectName", "field1", "error1"),
                new FieldError("objectName", "field2", "error2")
        );

        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        List<ExceptionArgumentResponse> errorList = fieldErrors.stream()
                .map(ExceptionArgumentResponse::new)
                .toList();

        ResponseEntity<List<ExceptionArgumentResponse>> responseEntityMock = mock(ResponseEntity.class);
        when(responseEntityMock.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(responseEntityMock.getBody()).thenReturn(errorList);


        ResponseEntity<List<ExceptionArgumentResponse>> responseEntity = controllerAdvisor.handlerArgumentInvalidException(exception);


        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorList.size(), responseEntity.getBody().size());
        assertEquals(errorList.get(0).getField(), responseEntity.getBody().get(0).getField());
        assertEquals(errorList.get(0).getMessage(), responseEntity.getBody().get(0).getMessage());
        assertEquals(errorList.get(1).getField(), responseEntity.getBody().get(1).getField());
        assertEquals(errorList.get(1).getMessage(), responseEntity.getBody().get(1).getMessage());
    }

    @Test
    @DisplayName("Handle Element Not Found Exception")
    void testHandleElementNotFoundException() {

        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<ExceptionResponse> responseEntityMock = mock(ResponseEntity.class);
        when(responseEntityMock.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);
        when(responseEntityMock.getBody()).thenReturn(new ExceptionResponse(
                Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));


        ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.handleElementNotFoundException();


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE, responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.NOT_FOUND.toString(), responseEntity.getBody().getStatus());
        assertEquals(LocalDateTime.now().getDayOfYear(), responseEntity.getBody().getTimestamp().getDayOfYear());
    }

    @Test
    @DisplayName("Handle Identity Document Already Exists Exception")
    void testHandleIdentityDocumentAlreadyExistsException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        ResponseEntity<ExceptionResponse> expectedResponse = new ResponseEntity<>(
                new ExceptionResponse(
                        Constants.IDENTIFICATION_DOCUMENT_ALREADY_EXISTS,
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );

        ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.handleIdentificationDocumentAlreadyExistsException();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(Constants.IDENTIFICATION_DOCUMENT_ALREADY_EXISTS, responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.toString(), responseEntity.getBody().getStatus());
        assertEquals(LocalDateTime.now().getDayOfYear(), responseEntity.getBody().getTimestamp().getDayOfYear());
    }

    @Test
    @DisplayName("Handle Invalid Arguments Email Exception")
    void testHandleInvalidArgumentsEmailException() {
        ControllerAdvisor controllerAdvisor = new ControllerAdvisor();
        InvalidArgumentsEmailException exception = new InvalidArgumentsEmailException();
        ResponseEntity<ExceptionResponse> expectedResponse = new ResponseEntity<>(
                new ExceptionResponse(
                        String.format(Constants.ARGUMENTS_EMAIL_NOT_VALID_EXCEPTION_MESSAGE, exception.getMessage()),
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );

        ResponseEntity<ExceptionResponse> responseEntity = controllerAdvisor.handleInvalidArgumentsEmailException();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(Constants.ARGUMENTS_EMAIL_NOT_VALID_EXCEPTION_MESSAGE, responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.toString(), responseEntity.getBody().getStatus());
        assertEquals(LocalDateTime.now().getDayOfYear(), responseEntity.getBody().getTimestamp().getDayOfYear());
    }
}
