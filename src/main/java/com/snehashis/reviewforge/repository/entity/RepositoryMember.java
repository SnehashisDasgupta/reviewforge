package com.snehashis.reviewforge.repository.entity;

import com.snehashis.reviewforge.common.entity.AuditableEntity;
import com.snehashis.reviewforge.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repository_members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RepositoryMember extends AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "repository_id", nullable = false)
    private CodeRepository repository;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RepositoryMemberRole role;

    public RepositoryMember(CodeRepository repository, User user, RepositoryMemberRole role) {
        this.repository = repository;
        this.user = user;
        this.role = role;
    }

    public void updateRole(RepositoryMemberRole role) {
        this.role = role;
    }
}
