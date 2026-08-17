package com.snehashis.reviewforge.repository.dto.request;

import com.snehashis.reviewforge.repository.entity.RepositoryMemberRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddRepositoryMemberRequest {

    @NotBlank(message = "User email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotNull(message = "Repository member role is required.")
    private RepositoryMemberRole role;
}
