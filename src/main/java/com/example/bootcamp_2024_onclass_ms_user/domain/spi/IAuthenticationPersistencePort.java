package com.example.bootcamp_2024_onclass_ms_user.domain.spi;

import com.example.bootcamp_2024_onclass_ms_user.domain.model.Authentication;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Token;

public interface IAuthenticationPersistencePort {

    Token authenticate(Authentication authentication);
}
