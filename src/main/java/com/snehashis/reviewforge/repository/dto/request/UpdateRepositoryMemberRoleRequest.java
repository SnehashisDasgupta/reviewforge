package com.snehashis.reviewforge.repository.dto.request;

import com.snehashis.reviewforge.repository.entity.RepositoryMemberRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRepositoryMemberRoleRequest {

    @NotNull(message = "Repository member role is required.")
    private RepositoryMemberRole role;
}
