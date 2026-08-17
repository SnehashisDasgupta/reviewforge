package com.snehashis.reviewforge.user.service.impl;

import com.snehashis.reviewforge.common.exception.ResourceNotFoundException;
import com.snehashis.reviewforge.common.security.CurrentUserService;
import com.snehashis.reviewforge.user.dto.response.UserResponse;
import com.snehashis.reviewforge.user.entity.User;
import com.snehashis.reviewforge.user.repository.UserRepository;
import com.snehashis.reviewforge.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with email: " + email));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserResponse getCurrentUser() {
        User currentUser = currentUserService.getCurrentUser();

        return UserResponse.builder()
                .id(currentUser.getId())
                .fullName(currentUser.getFullName())
                .email(currentUser.getEmail())
                .role(currentUser.getRole())
                .status(currentUser.getStatus())
                .createdAt(currentUser.getCreatedAt())
                .updatedAt(currentUser.getUpdatedAt())
                .build();
    }
}
