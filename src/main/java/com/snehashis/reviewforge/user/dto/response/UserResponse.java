package com.snehashis.reviewforge.user.dto.response;

import com.snehashis.reviewforge.user.entity.UserRole;
import com.snehashis.reviewforge.user.entity.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class UserResponse {

    private UUID id;
    private String fullName;
    private String email;
    private UserRole role;
    private UserStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
