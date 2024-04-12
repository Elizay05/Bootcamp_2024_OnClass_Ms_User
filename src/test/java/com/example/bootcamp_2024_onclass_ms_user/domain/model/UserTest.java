package com.example.bootcamp_2024_onclass_ms_user.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    @DisplayName("When_Getters_Expect_Success")
    void getters_Success() {
        User user = new User(1L, "John", "Doe", "123456789", "555-5555", "john.doe@example.com", 1L, "password");

        assertEquals(1L, user.getId());
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getLastName());
        assertEquals("123456789", user.getIdentificationDocument());
        assertEquals("555-5555", user.getCellphoneNumber());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(1L, user.getRolId());
        assertEquals("", user.getRolName());
        assertEquals("password", user.getPassword());
    }

    @Test
    @DisplayName("When_Setters_Expect_Success")
    void setters_Success() {
        User user = new User(1L, "John", "Doe", "123456789", "555-5555", "john.doe@example.com", 1L, "password");

        user.setRolName("Admin");
        user.setPassword("newpassword");

        assertEquals("Admin", user.getRolName());
        assertEquals("newpassword", user.getPassword());
    }
}
