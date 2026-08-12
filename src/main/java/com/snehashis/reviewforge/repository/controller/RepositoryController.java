package com.snehashis.reviewforge.repository.controller;

import com.snehashis.reviewforge.common.response.ApiResponse;
import com.snehashis.reviewforge.repository.dto.request.CreateRepositoryRequest;
import com.snehashis.reviewforge.repository.dto.request.UpdateRepositoryRequest;
import com.snehashis.reviewforge.repository.dto.response.RepositoryResponse;
import com.snehashis.reviewforge.repository.service.RepositoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/repositories")
@RequiredArgsConstructor
public class RepositoryController {

    private final RepositoryService repositoryService;

    @Operation(summary = "Create Repository")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RepositoryResponse> createRepository(
            @RequestBody CreateRepositoryRequest request
            ) {
        RepositoryResponse response = repositoryService.createRepository(request);

        return ApiResponse.<RepositoryResponse>builder()
                .success(true)
                .message("Repository created successfully.")
                .data(response)
                .timestamp(Instant.now())
                .build();
    }

    @Operation(summary = "Get My Repositories")
    @GetMapping
    public ApiResponse<List<RepositoryResponse>> getMyRepositories() {

        return ApiResponse.<List<RepositoryResponse>>builder()
                .success(true)
                .message("Repositories fetched successfully.")
                .data(repositoryService.getMyRepositories())
                .timestamp(Instant.now())
                .build();
    }

    @Operation(summary = "Get Repository By ID")
    @GetMapping("/{repoId}")
    public ApiResponse<RepositoryResponse> getRepositoryById(
            @PathVariable UUID repoId
    ) {
        return ApiResponse.<RepositoryResponse>builder()
                .success(true)
                .message("Repository fetched successfully.")
                .data(repositoryService.getRepositoryById(repoId))
                .timestamp(Instant.now())
                .build();
    }

    @Operation(summary = "Update Repository")
    @PutMapping("/{repoId}")
    public ApiResponse<RepositoryResponse> updateRepository(
            @PathVariable UUID repoId,
            @Valid  @RequestBody UpdateRepositoryRequest request
    ) {

        RepositoryResponse response = repositoryService.updateRepository(
                repoId,
                request
        );

        return ApiResponse.<RepositoryResponse>builder()
                .success(true)
                .message("Repository updated successfully.")
                .data(response)
                .timestamp(Instant.now())
                .build();
    }

    @Operation(summary = "Delete Repository")
    @DeleteMapping("/{repoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRepository(
            @PathVariable UUID repoId
    ) {
        repositoryService.deleteRepository(repoId);
    }
}
