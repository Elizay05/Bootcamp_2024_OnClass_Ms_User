package com.example.bootcamp_2024_onclass_ms_user.configuration.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
