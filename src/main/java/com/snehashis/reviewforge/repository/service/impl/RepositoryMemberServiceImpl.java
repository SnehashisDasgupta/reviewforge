package com.snehashis.reviewforge.repository.service.impl;

import com.snehashis.reviewforge.common.exception.ConflictException;
import com.snehashis.reviewforge.common.exception.ForbiddenException;
import com.snehashis.reviewforge.common.exception.ResourceNotFoundException;
import com.snehashis.reviewforge.common.security.CurrentUserService;
import com.snehashis.reviewforge.repository.dto.request.AddRepositoryMemberRequest;
import com.snehashis.reviewforge.repository.dto.request.UpdateRepositoryMemberRoleRequest;
import com.snehashis.reviewforge.repository.dto.response.RepositoryMemberResponse;
import com.snehashis.reviewforge.repository.entity.CodeRepository;
import com.snehashis.reviewforge.repository.entity.RepositoryMember;
import com.snehashis.reviewforge.repository.repository.CodeRepositoryRepository;
import com.snehashis.reviewforge.repository.repository.RepositoryMemberRepository;
import com.snehashis.reviewforge.repository.service.RepositoryMemberService;
import com.snehashis.reviewforge.user.entity.User;
import com.snehashis.reviewforge.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RepositoryMemberServiceImpl implements RepositoryMemberService {

    private final RepositoryMemberRepository repositoryMemberRepository;
    private final CodeRepositoryRepository  codeRepositoryRepository;
    private final UserService userService;
    private final CurrentUserService currentUserService;

    @Override
    public void addMember(UUID repoId, AddRepositoryMemberRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        CodeRepository repository = codeRepositoryRepository
                .findById(repoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repository not found with id: " + repoId
                        )
                );

        if (!repository.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException(
                    "Only the repository owner can add members."
            );
        }

        User memberUser = userService.findByEmail(request.getEmail());

        if(repositoryMemberRepository
                .existsByRepositoryIdAndUserId(
                        repoId,
                        memberUser.getId()
                )
        ){
            throw new ConflictException(
                    "User is already a member of this repository."
            );
        }

        if (repository.getOwner().getId().equals(memberUser.getId())) {
            throw new ConflictException(
                    "Repository owner cannot be added as a member."
            );
        }

        RepositoryMember member = new RepositoryMember(
                repository,
                memberUser,
                request.getRole()
        );

        repositoryMemberRepository.save(member);
    }

    @Override
    public List<RepositoryMemberResponse> getMembers(UUID repoId) {

        User currentUser = currentUserService.getCurrentUser();

        CodeRepository repository = codeRepositoryRepository
                .findById(repoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repository not found with id: " + repoId
                        )
                );

        boolean isOwner = repository.getOwner()
                .getId()
                .equals(currentUser.getId());

        boolean isMember = repositoryMemberRepository
                .existsByRepositoryIdAndUserId(
                        repoId,
                        currentUser.getId()
                );

        if (!isOwner && !isMember) {
            throw new ForbiddenException(
                    "You do not have permission to view repository members."
            );
        }

        return repositoryMemberRepository
                .findByRepositoryId(repoId)
                .stream()
                .map(member -> RepositoryMemberResponse.builder()
                        .userId(member.getUser().getId())
                        .fullName(member.getUser().getFullName())
                        .email(member.getUser().getEmail())
                        .role(member.getRole())
                        .createdAt(member.getCreatedAt())
                        .build()
                )
                .toList();
    }

    @Override
    public void removeMember(UUID repoId, UUID userId) {

        User currentUser = currentUserService.getCurrentUser();

        CodeRepository repository = codeRepositoryRepository
                .findById(repoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repository not found with id: " + repoId
                        )
                );

        if (!repository.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException(
                    "Only the repository owner can remove members."
            );
        }

        RepositoryMember member = repositoryMemberRepository
                .findByRepositoryIdAndUserId(repoId,userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repository member not found for user id: " + userId
                        )
                );

        repositoryMemberRepository.delete(member);
    }

    @Override
    public void updateMemberRole(UUID repoId, UUID userId, UpdateRepositoryMemberRoleRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        CodeRepository repository = codeRepositoryRepository
                .findById(repoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repository not found with id: " + repoId
                        )
                );

        if (!repository.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException(
                    "Only the repository owner can update member roles."
            );
        }

        RepositoryMember member = repositoryMemberRepository
                .findByRepositoryIdAndUserId(repoId, userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Repository member not found"
                        )
                );

        member.updateRole(request.getRole());

        repositoryMemberRepository.save(member);
    }
}
