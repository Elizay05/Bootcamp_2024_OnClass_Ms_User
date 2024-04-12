package com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserEntityTest {
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("When_UserEntityIsValid_Expect_NoViolations")
    void testUserEntityValidEntity() {
        Long id = 4L;
        String name = "Pablo";
        String lastName = "Perez";
        String identificationDocument = "1127532895";
        String cellphoneNumber = "3056987741";
        String email = "pablo.perez@uc.cl";
        String password = "pablo123";
        RolEntity rolEntity = new RolEntity();

        UserEntity entity = new UserEntity(id, name, lastName, identificationDocument, cellphoneNumber, email, password, rolEntity);

        var violations = validator.validate(entity);

        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("When_SettersMethodsIsValid_Expect_NoViolations")
    void testEntityValidation() {
        Long id = 4L;
        String name = "Pablo";
        String lastName = "Perez";
        String identificationDocument = "1127532895";
        String cellphoneNumber = "3056987741";
        String email = "pablo.perez@uc.cl";
        String password = "pablo123";
        RolEntity rolEntity = new RolEntity();

        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setLastName(lastName);
        entity.setIdentificationDocument(identificationDocument);
        entity.setCellphoneNumber(cellphoneNumber);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setRol(rolEntity);

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(entity);

        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Getter Methods Should Return Correct Values")
    void testGetterMethods() {
        Long id = 4L;
        String name = "Pablo";
        String lastName = "Perez";
        String identificationDocument = "1127532895";
        String cellphoneNumber = "3056987741";
        String email = "pablo.perez@uc.cl";
        String password = "pablo123";
        RolEntity rolEntity = new RolEntity();

        UserEntity entity = new UserEntity(id, name, lastName, identificationDocument, cellphoneNumber, email, password, rolEntity);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(lastName, entity.getLastName());
        assertEquals(identificationDocument, entity.getIdentificationDocument());
        assertEquals(cellphoneNumber, entity.getCellphoneNumber());
        assertEquals(email, entity.getEmail());
        assertEquals(password, entity.getPassword());
        assertEquals(rolEntity, entity.getRol());
    }

}
