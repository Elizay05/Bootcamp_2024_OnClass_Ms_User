package com.example.bootcamp_2024_onclass_ms_user.domain.api.usecase;

import com.example.bootcamp_2024_onclass_ms_user.domain.api.IAuthenticationServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Authentication;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Token;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IAuthenticationPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AuthenticationUseCaseTest {

    @Mock
    private IAuthenticationPersistencePort authenticationPersistencePort;

    @Mock
    private Token expectedToken;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("When_Authenticate_Expect_Success")
    void testAuthenticate_expectSuccess() {
        Authentication authentication = new Authentication("username", "password");

        Token expectedToken = new Token("mockToken");
        when(authenticationPersistencePort.authenticate(authentication)).thenReturn(expectedToken);

        Token actualToken = authenticationUseCase.authenticate(authentication);
        assertEquals(expectedToken, actualToken);

        verify(authenticationPersistencePort).authenticate(authentication);
    }
}
