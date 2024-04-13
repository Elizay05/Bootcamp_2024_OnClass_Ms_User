package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class AuthenticationRequest {

    private String email;

    private String password;
}
