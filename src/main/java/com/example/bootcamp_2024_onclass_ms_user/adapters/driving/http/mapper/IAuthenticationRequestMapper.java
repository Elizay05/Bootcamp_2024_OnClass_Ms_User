package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.request.AuthenticationRequest;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.Authentication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuthenticationRequestMapper {

    Authentication addRequestToAuthentication(AuthenticationRequest request);
}
