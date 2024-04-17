package com.example.bootcamp_2024_onclass_ms_user.domain.api.usecase;

import com.example.bootcamp_2024_onclass_ms_user.domain.api.IAuthenticationServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Authentication;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Token;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IAuthenticationPersistencePort;

public class AuthenticationUseCase implements IAuthenticationServicePort {

    private final IAuthenticationPersistencePort authenticationPersistencePort;

    public AuthenticationUseCase(IAuthenticationPersistencePort authenticationPersistencePort) {
        this.authenticationPersistencePort = authenticationPersistencePort;
    }

    @Override
    public Token authenticate(Authentication authentication) {
        return authenticationPersistencePort.authenticate(authentication);
    }

}
