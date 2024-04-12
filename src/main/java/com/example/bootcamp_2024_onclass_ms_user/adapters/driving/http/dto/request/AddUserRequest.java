package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddUserRequest {
    @NotBlank(message = "Name field cannot be empty")
    private final String name;

    @NotBlank(message = "Last name field cannot be empty")
    private final String lastName;

    @NotBlank(message = "Identification document field cannot be empty")
    private final String identificationDocument;

    @NotBlank(message = "Cellphone number field cannot be empty")
    private final String cellphoneNumber;

    @NotBlank(message = "Email field cannot be empty")
    @Email(message = "Email is not valid")
    private final String email;

    @NotNull(message = "Rol id field cannot be null")
    private final Long rolId;

    @NotBlank(message = "Password field cannot be empty")
    private final String password;
}
