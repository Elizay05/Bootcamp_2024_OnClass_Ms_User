package com.example.bootcamp_2024_onclass_ms_user.domain.api;


import com.example.bootcamp_2024_onclass_ms_user.domain.model.Authentication;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Token;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;

public interface IAuthenticationServicePort {

    Token authenticate(Authentication authentication);
}
