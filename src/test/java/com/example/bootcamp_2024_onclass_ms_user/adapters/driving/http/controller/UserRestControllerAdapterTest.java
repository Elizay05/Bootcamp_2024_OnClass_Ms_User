package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.controller;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.request.AddUserRequest;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response.UserResponse;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper.IUserResponseMapper;
import com.example.bootcamp_2024_onclass_ms_user.domain.api.IUserServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserRestControllerAdapterTest {

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IUserRequestMapper userRequestMapper;

    @Mock
    private IUserResponseMapper userResponseMapper;

    @InjectMocks
    private UserRestControllerAdapter userRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("When_AddUser_Expect_CorrectResponse")
    void testAddUser() {
        AddUserRequest request = new AddUserRequest("Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 1L, "123456");
        User expectedUser = new User(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 1L, "123456");
        UserResponse expectedResponse = new UserResponse(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", "ADMINISTRATOR", "123456");

        when(userRequestMapper.addRequestToUser(request)).thenReturn(expectedUser);
        when(userServicePort.saveUser(expectedUser)).thenReturn(expectedUser);
        when(userResponseMapper.toUserResponse(expectedUser)).thenReturn(expectedResponse);

        ResponseEntity<UserResponse> responseEntity = userRestControllerAdapter.addAdmin(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(userRequestMapper, times(1)).addRequestToUser(request);
        verify(userServicePort, times(1)).saveUser(expectedUser);
        verify(userResponseMapper, times(1)).toUserResponse(expectedUser);
    }
}
