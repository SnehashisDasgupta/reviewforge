package com.snehashis.reviewforge.repository.repository;

import com.snehashis.reviewforge.repository.entity.CodeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CodeRepositoryRepository extends JpaRepository<CodeRepository, UUID> {

    List<CodeRepository> findByOwnerId(UUID ownerId);
}
