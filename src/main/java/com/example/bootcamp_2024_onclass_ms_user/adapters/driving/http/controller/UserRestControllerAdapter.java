package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.controller;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.request.AddUserRequest;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response.UserResponse;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper.IUserResponseMapper;
import com.example.bootcamp_2024_onclass_ms_user.domain.api.IUserServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class UserRestControllerAdapter {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    @PostMapping("/admin")
    public ResponseEntity<UserResponse> addAdmin(@Valid @RequestBody AddUserRequest request) {
        User user = userRequestMapper.addRequestToUser(request);
        user = userServicePort.saveAdmin(user);
        UserResponse response = userResponseMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    @PostMapping("/tutor")
    public ResponseEntity<UserResponse> addTutor(@Valid @RequestBody AddUserRequest request) {
        User user = userRequestMapper.addRequestToUser(request);
        user = userServicePort.saveTutor(user);
        UserResponse response = userResponseMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR') or hasAnyAuthority('TUTOR')")
    @PostMapping("/student")
    public ResponseEntity<UserResponse> addStudent(@Valid @RequestBody AddUserRequest request) {
        User user = userRequestMapper.addRequestToUser(request);
        user = userServicePort.saveStudent(user);
        UserResponse response = userResponseMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
