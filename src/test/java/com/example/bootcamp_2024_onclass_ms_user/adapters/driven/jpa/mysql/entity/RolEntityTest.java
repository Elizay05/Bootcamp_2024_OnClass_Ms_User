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

class RolEntityTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("When_RolEntityIsValid_Expect_NoViolations")
    void testRolEntityValidEntity() {
        Long id = 1L;
        String name = "ADMINISTRATOR";
        String description = "You are an Administrator of the sistem";

        RolEntity entity = new RolEntity(id, name, description);

        var violations = validator.validate(entity);

        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("When_SettersMethodsIsValid_Expect_NoViolations")
    void testEntityValidation() {
        Long id = 1L;
        String name = "ADMINISTRATOR";
        String description = "You are an Administrator of the sistem";

        RolEntity entity = new RolEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setDescription(description);

        Set<ConstraintViolation<RolEntity>> violations = validator.validate(entity);

        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Getter Methods Should Return Correct Values")
    void testGetterMethods() {
        Long id = 1L;
        String name = "ADMINISTRATOR";
        String description = "You are an Administrator of the sistem";

        RolEntity entity = new RolEntity(id, name, description);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(description, entity.getDescription());
    }

}
