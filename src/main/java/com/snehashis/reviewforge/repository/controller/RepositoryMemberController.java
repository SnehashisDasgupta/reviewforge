package com.snehashis.reviewforge.repository.controller;

import com.snehashis.reviewforge.common.response.ApiResponse;
import com.snehashis.reviewforge.repository.dto.request.AddRepositoryMemberRequest;
import com.snehashis.reviewforge.repository.dto.request.UpdateRepositoryMemberRoleRequest;
import com.snehashis.reviewforge.repository.dto.response.RepositoryMemberResponse;
import com.snehashis.reviewforge.repository.service.RepositoryMemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/repositories/{repoId}/members")
@RequiredArgsConstructor
public class RepositoryMemberController {

    private final RepositoryMemberService repositoryMemberService;

    @Operation(summary = "Add Repository Member")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> addMember(
            @PathVariable UUID repoId,
            @Valid @RequestBody AddRepositoryMemberRequest request
    ) {
        repositoryMemberService.addMember(
                repoId,
                request
        );

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Repository member added successfully.")
                .data(null)
                .timestamp(Instant.now())
                .build();
    }

    @Operation(summary = "Get Repository Members")
    @GetMapping
    public ApiResponse<List<RepositoryMemberResponse>> getMembers(
            @PathVariable UUID repoId
    ) {

        List<RepositoryMemberResponse> members = repositoryMemberService
                .getMembers(repoId);

        return ApiResponse.<List<RepositoryMemberResponse>>builder()
                .success(true)
                .message("Repository members fetched successfully.")
                .data(members)
                .timestamp(Instant.now())
                .build();
    }

    @Operation(summary = "Remove Member")
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMember(
            @PathVariable UUID repoId,
            @PathVariable UUID userId
    ) {

        repositoryMemberService.removeMember(
                repoId,
                userId
        );
    }

    @Operation(summary = "Update Repository Member Role")
    @PatchMapping("/{userId}")
    public ApiResponse<Void> updateMemberRole(
            @PathVariable UUID repoId,
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateRepositoryMemberRoleRequest request
    ) {

        repositoryMemberService.updateMemberRole(
                repoId,
                userId,
                request
        );

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Repository member role updated successfully.")
                .data(null)
                .timestamp(Instant.now())
                .build();
    }
}
