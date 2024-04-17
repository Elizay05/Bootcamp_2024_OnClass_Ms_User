package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.controller;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.request.AuthenticationRequest;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response.TokenResponse;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper.IAuthenticationRequestMapper;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper.ITokenResponseMapper;
import com.example.bootcamp_2024_onclass_ms_user.domain.api.IAuthenticationServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Authentication;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationRestControllerAdapterTest {

    @Mock
    private IAuthenticationServicePort authenticationServicePort;

    @Mock
    private IAuthenticationRequestMapper authenticationRequestMapper;

    @Mock
    private ITokenResponseMapper tokenResponseMapper;

    @InjectMocks
    private AuthenticationRestControllerAdapter authenticationRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("When_login_Expect_CorrectResponse")
    void testLogin() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("samuel.velez@uc.cl")
                .password("123456")
                .build();

        Authentication expectedAuthentication = new Authentication("samuel.velez@uc.cl", "123456");

        Token expectedToken = new Token ("mockToken");

        TokenResponse tokenResponse = TokenResponse.builder()
                .token("mockToken")
                .build();

        when(authenticationRequestMapper.addRequestToAuthentication(authenticationRequest)).thenReturn(expectedAuthentication);
        when(authenticationServicePort.authenticate(expectedAuthentication)).thenReturn(expectedToken);
        when(tokenResponseMapper.toTokenResponse(expectedToken)).thenReturn(tokenResponse);

        ResponseEntity<TokenResponse> responseEntity = authenticationRestControllerAdapter.login(authenticationRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tokenResponse, responseEntity.getBody());
        verify(authenticationRequestMapper, times(1)).addRequestToAuthentication(authenticationRequest);
        verify(authenticationServicePort, times(1)).authenticate(expectedAuthentication);
        verify(tokenResponseMapper, times(1)).toTokenResponse(expectedToken);
    }
}
