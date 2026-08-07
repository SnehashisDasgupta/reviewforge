package com.snehashis.reviewforge.user.service;

import com.snehashis.reviewforge.user.dto.request.LoginRequest;
import com.snehashis.reviewforge.user.dto.request.RegisterRequest;
import com.snehashis.reviewforge.user.dto.response.AuthResponse;
import com.snehashis.reviewforge.user.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
