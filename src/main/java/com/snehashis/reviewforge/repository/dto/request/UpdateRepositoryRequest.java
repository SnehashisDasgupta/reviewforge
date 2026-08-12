package com.snehashis.reviewforge.repository.dto.request;

import com.snehashis.reviewforge.repository.entity.RepositoryVisibility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRepositoryRequest {

    @NotBlank(message = "Repository name is required.")
    @Size(max = 150, message = "Repository name must not exceed 150 characters.")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters.")
    private String description;

    @NotNull(message = "Repository visibility is required.")
    private RepositoryVisibility visibility;
}
