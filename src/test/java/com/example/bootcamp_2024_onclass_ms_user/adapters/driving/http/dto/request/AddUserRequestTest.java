package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AddUserRequestTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("When_GetterMethodsCalled_Expect_CorrectValues")
    void testAddVersionBootcampRequest_GetterMethods() {
        String name = "Pablo";
        String lastName = "Velez";
        String identificationDocument = "1127532405";
        String cellphoneNumber = "3056987741";
        String email = "pablo.velez@uc.cl";
        Long rolId = 1L;
        String password = "123456";

        AddUserRequest request = new AddUserRequest(name, lastName, identificationDocument, cellphoneNumber, email, rolId, password);

        assertNotNull(request);
        assertEquals(name, request.getName());
        assertEquals(lastName, request.getLastName());
        assertEquals(identificationDocument, request.getIdentificationDocument());
        assertEquals(cellphoneNumber, request.getCellphoneNumber());
        assertEquals(email, request.getEmail());
        assertEquals(rolId, request.getRolId());
        assertEquals(password, request.getPassword());
    }

    @Test
    @DisplayName("When_AddUserRequestWithBlankFields_Expect_FailValidation")
    void testAddUserRequest_BlankFields() {
        AddUserRequest request = new AddUserRequest("", "", "", "", "", 1L, "");

        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);

        assertEquals(6, violations.size());
        ConstraintViolation<AddUserRequest> violation = violations.iterator().next();
        assertEquals(NotBlank.class, violation.getConstraintDescriptor().getAnnotation().annotationType());
    }

    @Test
    @DisplayName("When_AddUserRequestWithNullField_Expect_FailValidation")
    void testAddUserRequest_NullField() {
        AddUserRequest request = new AddUserRequest("Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", null, "123456");

        Set<ConstraintViolation<AddUserRequest>> violations = validator.validate(request);

        assertEquals(1, violations.size());
        ConstraintViolation<AddUserRequest> violation = violations.iterator().next();
        assertEquals(NotNull.class, violation.getConstraintDescriptor().getAnnotation().annotationType());
    }
}
