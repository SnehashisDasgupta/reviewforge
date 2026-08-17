package com.snehashis.reviewforge.repository.dto.response;

import com.snehashis.reviewforge.repository.entity.RepositoryMemberRole;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class RepositoryMemberResponse {

    private UUID userId;
    private String fullName;
    private String email;
    private RepositoryMemberRole role;
    private Instant createdAt;
}
