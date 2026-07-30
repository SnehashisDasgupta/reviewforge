package com.snehashis.reviewforge.user.controller;

import com.snehashis.reviewforge.common.response.ApiResponse;
import com.snehashis.reviewforge.common.response.ApiResponseFactory;
import com.snehashis.reviewforge.user.dto.request.RegisterRequest;
import com.snehashis.reviewforge.user.dto.response.UserResponse;
import com.snehashis.reviewforge.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterRequest request){

        UserResponse userResponse = authService.register(request);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponseFactory.success(
                                userResponse,
                                "User registered successfully"
                        )
                );
    }
}
