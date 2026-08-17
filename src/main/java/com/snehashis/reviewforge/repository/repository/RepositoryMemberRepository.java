package com.snehashis.reviewforge.repository.repository;

import com.snehashis.reviewforge.repository.entity.RepositoryMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryMemberRepository extends JpaRepository<RepositoryMember, UUID> {

    boolean existsByRepositoryIdAndUserId(UUID repoId, UUID userId);
    List<RepositoryMember> findByRepositoryId(UUID repoId);
    Optional<RepositoryMember> findByRepositoryIdAndUserId(UUID repoId, UUID userId);
}
