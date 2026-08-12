package com.snehashis.reviewforge.repository.entity;

import com.snehashis.reviewforge.common.entity.AuditableEntity;
import com.snehashis.reviewforge.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repositories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeRepository extends AuditableEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RepositoryVisibility visibility;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public CodeRepository(String name, String description, RepositoryVisibility visibility, User owner) {
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.owner = owner;
    }

    public void update(String name, String description, RepositoryVisibility visibility) {
        this.name = name;
        this.description = description;
        this.visibility = visibility;
    }
}


