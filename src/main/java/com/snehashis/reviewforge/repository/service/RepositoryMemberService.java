package com.snehashis.reviewforge.repository.service;

import com.snehashis.reviewforge.repository.dto.request.AddRepositoryMemberRequest;
import com.snehashis.reviewforge.repository.dto.request.UpdateRepositoryMemberRoleRequest;
import com.snehashis.reviewforge.repository.dto.response.RepositoryMemberResponse;

import java.util.List;
import java.util.UUID;

public interface RepositoryMemberService {

    void addMember(UUID repoId, AddRepositoryMemberRequest request);
    List<RepositoryMemberResponse> getMembers(UUID repoId);
    void removeMember(UUID repoId, UUID userId);
    void updateMemberRole(UUID repoId, UUID userId, UpdateRepositoryMemberRoleRequest request);
}
