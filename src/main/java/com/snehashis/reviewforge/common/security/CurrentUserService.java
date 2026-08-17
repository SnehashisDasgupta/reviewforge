package com.snehashis.reviewforge.common.security;

import com.snehashis.reviewforge.common.exception.ResourceNotFoundException;
import com.snehashis.reviewforge.user.entity.User;
import com.snehashis.reviewforge.user.repository.UserRepository;
import com.snehashis.reviewforge.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserRepository userRepository;

    public User getCurrentUser() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Current user could not be found."
                        )
                );
    }
}
