package com.snehashis.reviewforge.user.service.impl;

import com.snehashis.reviewforge.common.exception.BadRequestException;
import com.snehashis.reviewforge.common.exception.ConflictException;
import com.snehashis.reviewforge.user.dto.request.RegisterRequest;
import com.snehashis.reviewforge.user.dto.response.UserResponse;
import com.snehashis.reviewforge.user.entity.User;
import com.snehashis.reviewforge.user.entity.UserRole;
import com.snehashis.reviewforge.user.entity.UserStatus;
import com.snehashis.reviewforge.user.mapper.UserMapper;
import com.snehashis.reviewforge.user.service.AuthService;
import com.snehashis.reviewforge.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userService.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email already exists");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Passwords don't match");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getFullName(),
                request.getEmail(),
                encodedPassword,
                UserRole.USER,
                UserStatus.ACTIVE
        );

        User savedUser = userService.save(user);
        return UserMapper.toResponse(savedUser);
    }
}
