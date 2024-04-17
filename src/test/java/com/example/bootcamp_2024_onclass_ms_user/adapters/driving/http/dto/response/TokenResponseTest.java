package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TokenResponseTest {

    @Test
    @DisplayName("When_Constructor_WithValidArguments_Expect_Success")
    void constructor_WithValidArguments_Success() {
        String tokenValue = "exampleToken";

        TokenResponse tokenResponse = TokenResponse.builder()
                .token(tokenValue)
                .build();

        assertEquals(tokenValue, tokenResponse.getToken());
    }


}
