package com.example.bootcamp_2024_onclass_ms_user.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationTest {

    @Test
    @DisplayName("When_Constructor_Expect_Success")
    void testConstructor() {
        String email = "test@example.com";
        String password = "password123";
        Authentication authentication = new Authentication(email, password);

        assertEquals(email, authentication.getEmail());
        assertEquals(password, authentication.getPassword());
    }
}
