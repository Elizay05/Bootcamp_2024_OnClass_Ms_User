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
    @DisplayName("When_FreeAddAdmin_Expect_CorrectResponse")
    void testFreeAddAdmin() {
        AddUserRequest requestAdmin = new AddUserRequest("Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 1L, "123456");
        User expectedUserAdmin = new User(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 1L, "123456");
        UserResponse expectedResponseAdmin = new UserResponse(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", "ADMINISTRATOR", "123456");

        when(userRequestMapper.addRequestToUser(requestAdmin)).thenReturn(expectedUserAdmin);
        when(userServicePort.saveAdmin(expectedUserAdmin)).thenReturn(expectedUserAdmin);
        when(userResponseMapper.toUserResponse(expectedUserAdmin)).thenReturn(expectedResponseAdmin);

        ResponseEntity<UserResponse> responseEntityAdmin = userRestControllerAdapter.freeAddAdmin(requestAdmin);

        assertEquals(HttpStatus.CREATED, responseEntityAdmin.getStatusCode());
        assertEquals(expectedResponseAdmin, responseEntityAdmin.getBody());

        verify(userRequestMapper, times(1)).addRequestToUser(requestAdmin);
        verify(userServicePort, times(1)).saveAdmin(expectedUserAdmin);
        verify(userResponseMapper, times(1)).toUserResponse(expectedUserAdmin);
    }

    @Test
    @DisplayName("When_AddAdmin_Expect_CorrectResponse")
    void testAddAdmin() {
        AddUserRequest requestAdmin = new AddUserRequest("Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 1L, "123456");
        User expectedUserAdmin = new User(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 1L, "123456");
        UserResponse expectedResponseAdmin = new UserResponse(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", "ADMINISTRATOR", "123456");

        when(userRequestMapper.addRequestToUser(requestAdmin)).thenReturn(expectedUserAdmin);
        when(userServicePort.saveAdmin(expectedUserAdmin)).thenReturn(expectedUserAdmin);
        when(userResponseMapper.toUserResponse(expectedUserAdmin)).thenReturn(expectedResponseAdmin);

        ResponseEntity<UserResponse> responseEntityAdmin = userRestControllerAdapter.addAdmin(requestAdmin);

        assertEquals(HttpStatus.CREATED, responseEntityAdmin.getStatusCode());
        assertEquals(expectedResponseAdmin, responseEntityAdmin.getBody());

        verify(userRequestMapper, times(1)).addRequestToUser(requestAdmin);
        verify(userServicePort, times(1)).saveAdmin(expectedUserAdmin);
        verify(userResponseMapper, times(1)).toUserResponse(expectedUserAdmin);
    }

    @Test
    @DisplayName("When_AddTutor_Expect_CorrectResponse")
    void testAddTutor() {
        AddUserRequest requestTutor = new AddUserRequest("Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 2L, "123456");
        User expectedUserTutor = new User(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 2L, "123456");
        UserResponse expectedResponseTutor = new UserResponse(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", "TUTOR", "123456");

        when(userRequestMapper.addRequestToUser(requestTutor)).thenReturn(expectedUserTutor);
        when(userServicePort.saveTutor(expectedUserTutor)).thenReturn(expectedUserTutor);
        when(userResponseMapper.toUserResponse(expectedUserTutor)).thenReturn(expectedResponseTutor);

        ResponseEntity<UserResponse> responseEntityTutor = userRestControllerAdapter.addTutor(requestTutor);

        assertEquals(HttpStatus.CREATED, responseEntityTutor.getStatusCode());
        assertEquals(expectedResponseTutor, responseEntityTutor.getBody());

        verify(userRequestMapper, times(1)).addRequestToUser(requestTutor);
        verify(userServicePort, times(1)).saveTutor(expectedUserTutor);
        verify(userResponseMapper, times(1)).toUserResponse(expectedUserTutor);
    }

    @Test
    @DisplayName("When_AddStudent_Expect_CorrectResponse")
    void testAddStudent() {
        AddUserRequest requestStudent = new AddUserRequest("Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 3L, "123456");
        User expectedUserStudent = new User(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", 3L, "123456");
        UserResponse expectedResponseStudent = new UserResponse(1L, "Samuel", "Velez", "1127532895", "3056987741", "samuel.velez@uc.cl", "STUDENT", "123456");

        when(userRequestMapper.addRequestToUser(requestStudent)).thenReturn(expectedUserStudent);
        when(userServicePort.saveStudent(expectedUserStudent)).thenReturn(expectedUserStudent);
        when(userResponseMapper.toUserResponse(expectedUserStudent)).thenReturn(expectedResponseStudent);

        ResponseEntity<UserResponse> responseEntityStudent = userRestControllerAdapter.addStudent(requestStudent);

        assertEquals(HttpStatus.CREATED, responseEntityStudent.getStatusCode());
        assertEquals(expectedResponseStudent, responseEntityStudent.getBody());

        verify(userRequestMapper, times(1)).addRequestToUser(requestStudent);
        verify(userServicePort, times(1)).saveStudent(expectedUserStudent);
        verify(userResponseMapper, times(1)).toUserResponse(expectedUserStudent);
    }
}
