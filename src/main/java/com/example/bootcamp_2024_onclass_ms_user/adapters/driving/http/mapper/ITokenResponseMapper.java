package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper;


import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response.TokenResponse;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response.UserResponse;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Token;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITokenResponseMapper {

    TokenResponse toTokenResponse(Token token);
}
