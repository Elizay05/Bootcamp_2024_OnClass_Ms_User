package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserResponseTest {

    @Test
    @DisplayName("When_ConstructorWithValidArguments_Expect_Success")
    void constructor_WithValidArguments_Success() {
        Long id = 1L;
        String name = "Pablo";
        String lastName = "Velez";
        String identificationDocument = "1127532895";
        String cellphoneNumber = "3056987741";
        String email = "pablo.velez@uc.cl";
        String rolName = "ADMINISTRATOR";
        String password = "123456";

        UserResponse userResponse = new UserResponse(id, name, lastName, identificationDocument, cellphoneNumber, email, rolName, password);

        assertNotNull(userResponse);
        assertEquals(id, userResponse.getId());
        assertEquals(name, userResponse.getName());
        assertEquals(lastName, userResponse.getLastName());
        assertEquals(identificationDocument, userResponse.getIdentificationDocument());
        assertEquals(cellphoneNumber, userResponse.getCellphoneNumber());
        assertEquals(email, userResponse.getEmail());
        assertEquals(rolName, userResponse.getRolName());
        assertEquals(password, userResponse.getPassword());
    }
}
