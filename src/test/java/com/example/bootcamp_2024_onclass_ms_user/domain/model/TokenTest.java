package com.example.bootcamp_2024_onclass_ms_user.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenTest {

    @Test
    @DisplayName("When_GetToken_Expect_Success")
    void testGetToken() {
        String tokenValue = "exampleToken";
        Token token = new Token(tokenValue);

        assertEquals(tokenValue, token.getToken());
    }
}
