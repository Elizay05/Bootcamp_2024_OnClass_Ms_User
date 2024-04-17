package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthenticationRequestTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("When_GetterMethodsCalled_Expect_CorrectValues")
    void testAAuthenticationRequest_GetterMethods() {
        String email = "pablo.velez@uc.cl";
        String password = "123456";

        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email(email)
                .password(password)
                .build();

        assertNotNull(authenticationRequest);
        assertEquals(email, authenticationRequest.getEmail());
        assertEquals(password, authenticationRequest.getPassword());
    }

    @Test
    @DisplayName("When_AuthenticationRequestWithBlankFields_Expect_FailValidation")
    void testAuthenticationRequest_BlankFields() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("")
                .password("")
                .build();

        Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(authenticationRequest);

        assertEquals(2, violations.size());
        ConstraintViolation<AuthenticationRequest> violation = violations.iterator().next();
        assertEquals(NotBlank.class, violation.getConstraintDescriptor().getAnnotation().annotationType());
    }

    @Test
    @DisplayName("When_AuthenticationRequestWithInvalidEmail_Expect_FailValidation")
    void testAuthenticationRequest_InvalidEmail() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("jeje")
                .password("123456")
                .build();

        Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(authenticationRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<AuthenticationRequest> violation = violations.iterator().next();
        assertEquals(Email.class, violation.getConstraintDescriptor().getAnnotation().annotationType());
    }
}
