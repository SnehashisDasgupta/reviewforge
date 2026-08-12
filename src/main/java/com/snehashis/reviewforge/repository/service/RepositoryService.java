package com.snehashis.reviewforge.repository.service;

import com.snehashis.reviewforge.repository.dto.request.CreateRepositoryRequest;
import com.snehashis.reviewforge.repository.dto.request.UpdateRepositoryRequest;
import com.snehashis.reviewforge.repository.dto.response.RepositoryResponse;

import java.util.List;
import java.util.UUID;

public interface RepositoryService {

    RepositoryResponse createRepository(CreateRepositoryRequest request);
    List<RepositoryResponse> getMyRepositories();
    RepositoryResponse getRepositoryById(UUID repoId);
    RepositoryResponse updateRepository(UUID repoId, UpdateRepositoryRequest request);
    void deleteRepository(UUID repoId);
}
