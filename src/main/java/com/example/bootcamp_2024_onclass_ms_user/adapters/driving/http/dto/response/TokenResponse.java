package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TokenResponse {

    private String token;
}
