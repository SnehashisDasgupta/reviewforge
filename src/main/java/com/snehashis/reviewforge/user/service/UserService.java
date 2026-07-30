package com.snehashis.reviewforge.user.service;

import com.snehashis.reviewforge.user.entity.User;

import java.util.UUID;

public interface UserService {

    User findById(UUID id);
    User findByEmail(String email);
    boolean existsByEmail(String email);
    User save(User user);
}
