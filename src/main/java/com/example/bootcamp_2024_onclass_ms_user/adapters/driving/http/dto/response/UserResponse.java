package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
    private final Long id;
    private final String name;
    private final String lastName;
    private final String identificationDocument;
    private final String cellphoneNumber;
    private final String email;
    private final String rolName;
    private final String password;
}
