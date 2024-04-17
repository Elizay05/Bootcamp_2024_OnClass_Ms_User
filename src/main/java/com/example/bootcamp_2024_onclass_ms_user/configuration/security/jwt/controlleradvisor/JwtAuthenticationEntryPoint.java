package com.example.bootcamp_2024_onclass_ms_user.configuration.security.jwt.controlleradvisor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String errorMessage = "Acceso no autorizado";
        if (authException != null) {
            errorMessage = authException.getMessage();
        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", errorMessage);

        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
