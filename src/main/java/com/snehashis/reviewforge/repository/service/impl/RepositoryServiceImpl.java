package com.snehashis.reviewforge.repository.service.impl;

import com.snehashis.reviewforge.common.exception.ForbiddenException;
import com.snehashis.reviewforge.common.exception.ResourceNotFoundException;
import com.snehashis.reviewforge.repository.dto.request.CreateRepositoryRequest;
import com.snehashis.reviewforge.repository.dto.request.UpdateRepositoryRequest;
import com.snehashis.reviewforge.repository.dto.response.RepositoryResponse;
import com.snehashis.reviewforge.repository.entity.CodeRepository;
import com.snehashis.reviewforge.repository.repository.CodeRepositoryRepository;
import com.snehashis.reviewforge.repository.service.RepositoryService;
import com.snehashis.reviewforge.user.entity.User;
import com.snehashis.reviewforge.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {

    private final CodeRepositoryRepository codeRepositoryRepository;
    private final UserService userService;

    @Override
    public RepositoryResponse createRepository(CreateRepositoryRequest request) {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();
        User owner = userService.findByEmail(email);

        CodeRepository repository = new CodeRepository(
                request.getName(),
                request.getDescription(),
                request.getVisibility(),
                owner
        );

        CodeRepository savedRepository = codeRepositoryRepository
                .save(repository);

        return RepositoryResponse.builder()
                .id(savedRepository.getId())
                .name(savedRepository.getName())
                .description(savedRepository.getDescription())
                .visibility(savedRepository.getVisibility())
                .ownerId(savedRepository.getOwner().getId())
                .createdAt(savedRepository.getCreatedAt())
                .updatedAt(savedRepository.getUpdatedAt())
                .build();
    }

    @Override
    public List<RepositoryResponse> getMyRepositories() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        User owner = userService.findByEmail(email);

        return codeRepositoryRepository.findByOwnerId(owner.getId())
                .stream()
                .map(repository -> RepositoryResponse.builder()
                        .id(repository.getId())
                        .name(repository.getName())
                        .description(repository.getDescription())
                        .visibility(repository.getVisibility())
                        .ownerId(repository.getOwner().getId())
                        .createdAt(repository.getCreatedAt())
                        .updatedAt(repository.getUpdatedAt())
                        .build()
                )
                .toList();
    }

    @Override
    public RepositoryResponse getRepositoryById(UUID repoId) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        User currentUser = userService.findByEmail(email);

        CodeRepository repository = codeRepositoryRepository
                .findById(repoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repository not found with id: " + repoId
                        )
                );

        if (!repository.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException(
                    "You do not have permission to access this repository."
            );
        }

        return RepositoryResponse.builder()
                .id(repository.getId())
                .name(repository.getName())
                .description(repository.getDescription())
                .visibility(repository.getVisibility())
                .ownerId(repository.getOwner().getId())
                .createdAt(repository.getCreatedAt())
                .updatedAt(repository.getUpdatedAt())
                .build();
    }

    @Override
    public RepositoryResponse updateRepository(UUID repoId, UpdateRepositoryRequest request) {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        User currentUser = userService.findByEmail(email);

        CodeRepository repository = codeRepositoryRepository
                .findById(repoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repository not found with id: " + repoId
                        )
                );

        if(!repository.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException(
                    "You do not have permission to update this repository."
            );
        }

        repository.update(
                request.getName(),
                request.getDescription(),
                request.getVisibility()
        );

        CodeRepository updatedRepository = codeRepositoryRepository
                .save(repository);

        return RepositoryResponse.builder()
                .id(updatedRepository.getId())
                .name(updatedRepository.getName())
                .description(updatedRepository.getDescription())
                .visibility(updatedRepository.getVisibility())
                .ownerId(updatedRepository.getOwner().getId())
                .createdAt(updatedRepository.getCreatedAt())
                .updatedAt(updatedRepository.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteRepository(UUID repoId) {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        User currentUser = userService.findByEmail(email);

        CodeRepository repository = codeRepositoryRepository
                .findById(repoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repository not found with id: " + repoId
                        )
                );

        if(!repository.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException(
                    "You do not have permission to delete this repository."
            );
        }

        codeRepositoryRepository.delete(repository);
    }
}
