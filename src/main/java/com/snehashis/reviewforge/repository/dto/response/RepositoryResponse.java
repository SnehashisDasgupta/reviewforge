package com.snehashis.reviewforge.repository.dto.response;

import com.snehashis.reviewforge.repository.entity.RepositoryVisibility;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class RepositoryResponse {

    private UUID id;
    private String name;
    private String description;
    private RepositoryVisibility visibility;
    private UUID ownerId;
    private Instant createdAt;
    private Instant updatedAt;
}
