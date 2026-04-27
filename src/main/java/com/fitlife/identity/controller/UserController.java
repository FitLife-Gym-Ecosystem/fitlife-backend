package com.fitlife.identity.controller;

import com.fitlife.core.response.ApiResponse;
import com.fitlife.identity.dto.UserCreationRequest;
import com.fitlife.identity.dto.UserResponse;
import com.fitlife.identity.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Quản lý tài khoản hệ thống")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Tạo user hệ thống", description = "Tạo tài khoản người dùng nội bộ với role được chỉ định.")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody UserCreationRequest request) {
        UserResponse result = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(result, "User created successfully"));
    }
}