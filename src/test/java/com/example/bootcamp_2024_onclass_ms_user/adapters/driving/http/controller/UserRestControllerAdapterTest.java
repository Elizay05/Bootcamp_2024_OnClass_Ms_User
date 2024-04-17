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
        AddUserRequest requestAdmin = new AddUserRequest("Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 1L, "123456");
        AddUserRequest requestTutor = new AddUserRequest("Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 2L, "123456");

        User expectedUserAdmin = new User(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 1L, "123456");
        User expectedUserTutor = new User(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 2L, "123456");

        UserResponse expectedResponseAdmin = new UserResponse(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", "ADMINISTRATOR", "123456");
        UserResponse expectedResponseTutor = new UserResponse(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", "TUTOR", "123456");

        when(userRequestMapper.addRequestToUser(requestAdmin)).thenReturn(expectedUserAdmin);
        when(userRequestMapper.addRequestToUser(requestTutor)).thenReturn(expectedUserTutor);


        when(userServicePort.saveAdmin(expectedUserAdmin)).thenReturn(expectedUserAdmin);
        when(userServicePort.saveTutor(expectedUserTutor)).thenReturn(expectedUserTutor);

        when(userResponseMapper.toUserResponse(expectedUserAdmin)).thenReturn(expectedResponseAdmin);
        when(userResponseMapper.toUserResponse(expectedUserTutor)).thenReturn(expectedResponseTutor);


        ResponseEntity<UserResponse> responseEntityAdmin = userRestControllerAdapter.addAdmin(requestAdmin);
        ResponseEntity<UserResponse> responseEntityTutor = userRestControllerAdapter.addTutor(requestTutor);

        assertEquals(HttpStatus.CREATED, responseEntityAdmin.getStatusCode());
        assertEquals(HttpStatus.CREATED, responseEntityTutor.getStatusCode());

        assertEquals(expectedResponseAdmin, responseEntityAdmin.getBody());
        assertEquals(expectedResponseTutor, responseEntityTutor.getBody());

        verify(userRequestMapper, times(1)).addRequestToUser(requestAdmin);
        verify(userRequestMapper, times(1)).addRequestToUser(requestTutor);

        verify(userServicePort, times(1)).saveAdmin(expectedUserAdmin);
        verify(userServicePort, times(1)).saveTutor(expectedUserTutor);

        verify(userResponseMapper, times(1)).toUserResponse(expectedUserAdmin);
        verify(userResponseMapper, times(1)).toUserResponse(expectedUserTutor);
    }

}
