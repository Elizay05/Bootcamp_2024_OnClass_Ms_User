package com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.adapter;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.bootcamp_2024_onclass_ms_user.configuration.security.CustomUserDetails;
import com.example.bootcamp_2024_onclass_ms_user.configuration.security.jwt.JwtService;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Authentication;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Token;
import com.example.bootcamp_2024_onclass_ms_user.domain.spi.IAuthenticationPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@RequiredArgsConstructor
public class AuthenticationAdapter implements IAuthenticationPersistencePort {

    private final IUserRepository repository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public Token authenticate(Authentication authentication) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authentication.getEmail(),
                        authentication.getPassword()
                )
        );
        var userEntity = repository.findByEmail(authentication.getEmail())
                .orElseThrow();
        CustomUserDetails user = new CustomUserDetails(userEntity);
        var jwtToken = jwtService.generateToken(user);
        return new Token(jwtToken);
    }

}
