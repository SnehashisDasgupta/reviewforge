package com.snehashis.reviewforge.user.controller;

import com.snehashis.reviewforge.common.response.ApiResponse;
import com.snehashis.reviewforge.user.dto.response.UserResponse;
import com.snehashis.reviewforge.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get Current User")
    @GetMapping("/me")
    public ApiResponse<UserResponse> getCurrentUser() {

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("Current user fetched successfully.")
                .data(userService.getCurrentUser())
                .timestamp(Instant.now())
                .build();
    }
}
