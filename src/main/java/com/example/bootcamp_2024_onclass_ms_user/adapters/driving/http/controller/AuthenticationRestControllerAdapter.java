package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.controller;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.request.AuthenticationRequest;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response.TokenResponse;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper.IAuthenticationRequestMapper;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper.ITokenResponseMapper;
import com.example.bootcamp_2024_onclass_ms_user.domain.api.IAuthenticationServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Authentication;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Token;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestControllerAdapter {

    private final IAuthenticationServicePort authenticationServicePort;
    private final IAuthenticationRequestMapper authenticationRequestMapper;
    private final ITokenResponseMapper tokenResponseMapper;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login (@Valid @RequestBody AuthenticationRequest request) {
        Authentication authentication = authenticationRequestMapper.addRequestToAuthentication(request);
        Token token = authenticationServicePort.authenticate(authentication);
        TokenResponse response = tokenResponseMapper.toTokenResponse(token);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
