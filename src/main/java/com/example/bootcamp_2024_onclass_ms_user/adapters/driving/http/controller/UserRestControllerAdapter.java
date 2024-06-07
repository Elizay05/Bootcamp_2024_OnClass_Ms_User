package com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.controller;

import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.request.AddUserRequest;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.dto.response.UserResponse;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.bootcamp_2024_onclass_ms_user.adapters.driving.http.mapper.IUserResponseMapper;
import com.example.bootcamp_2024_onclass_ms_user.domain.api.IUserServicePort;
import com.example.bootcamp_2024_onclass_ms_user.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a free ADMINISTRATOR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correct create a new administrator",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "The email or identification document already exists in the system or the role is not valid or request fields are invalid", content = @Content),
    })
    @PostMapping("/freeAdmin")
    public ResponseEntity<UserResponse> freeAddAdmin(@Valid @RequestBody AddUserRequest request) {
        User user = userRequestMapper.addRequestToUser(request);
        user = userServicePort.saveAdmin(user);
        UserResponse response = userResponseMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Create a new ADMINISTRATOR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correct create a new administrator",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "The email or identification document already exists in the system or the role is not valid or request fields are invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content),
            @ApiResponse(responseCode = "401", ref = "#/components/responses/UnauthorizedError")
    })
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    @PostMapping("/admin")
    public ResponseEntity<UserResponse> addAdmin(@Valid @RequestBody AddUserRequest request) {
        User user = userRequestMapper.addRequestToUser(request);
        user = userServicePort.saveAdmin(user);
        UserResponse response = userResponseMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Create a new TUTOR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correct create a new tutor",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "The email or identification document already exists in the system or the role is not valid or request fields are invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content),
            @ApiResponse(responseCode = "401", ref = "#/components/responses/UnauthorizedError")
    })
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    @PostMapping("/tutor")
    public ResponseEntity<UserResponse> addTutor(@Valid @RequestBody AddUserRequest request) {
        User user = userRequestMapper.addRequestToUser(request);
        user = userServicePort.saveTutor(user);
        UserResponse response = userResponseMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @Operation(summary = "Create a new STUDENT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correct create a new student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "The email or identification document already exists in the system or the role is not valid or request fields are invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content),
            @ApiResponse(responseCode = "401", ref = "#/components/responses/UnauthorizedError")
    })
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR') or hasAnyAuthority('TUTOR')")
    @PostMapping("/student")
    public ResponseEntity<UserResponse> addStudent(@Valid @RequestBody AddUserRequest request) {
        User user = userRequestMapper.addRequestToUser(request);
        user = userServicePort.saveStudent(user);
        UserResponse response = userResponseMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
