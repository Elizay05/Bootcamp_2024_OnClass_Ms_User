package com.example.bootcamp_2024_onclass_ms_user.domain.api.usecase;

import com.example.bootcamp_2024_onclass_ms_user.domain.api.IAuthenticationServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.exception.InvalidArgumentsEmailException;
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
        if (!isValidEmail(authentication.getEmail())) {
            throw new InvalidArgumentsEmailException();
        }
        return authenticationPersistencePort.authenticate(authentication);
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
        return email.matches(regex);
    }

}
