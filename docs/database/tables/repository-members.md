# Repository Members Table Design

## Purpose

The `repository_members` table manages collaboration within repositories.

Instead of using a simple many-to-many relationship between users and repositories, ReviewForge models membership as a dedicated entity. This allows us to store additional business information such as roles, join dates, invitation history, and future audit data.

Every user who has access to a repository (except the owner) is represented by a record in this table.

---

# Responsibilities

The `repository_members` table is responsible for:

- Managing repository collaboration
- Storing member roles
- Controlling repository permissions
- Recording when users joined a repository
- Supporting future role changes and audit history

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| users | Many Memberships → One User |
| repositories | Many Memberships → One Repository |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| repository_id | UUID | ❌ | Associated repository |
| user_id | UUID | ❌ | Member user |
| role | VARCHAR(30) | ❌ | Repository role |
| joined_at | TIMESTAMP WITH TIME ZONE | ❌ | Date the user joined |
| created_at | TIMESTAMP WITH TIME ZONE | ❌ | Record creation timestamp |
| updated_at | TIMESTAMP WITH TIME ZONE | ❌ | Last modification timestamp |

---

# Primary Key

```text
PRIMARY KEY (id)
```

---

# Foreign Keys

```text
repository_id REFERENCES repositories(id)

user_id REFERENCES users(id)
```

Both foreign keys are mandatory.

A membership cannot exist without both a valid repository and a valid user.

---

# Unique Constraints

A user may only have one active membership in a repository.

```text
UNIQUE (repository_id, user_id)
```

---

# Allowed Roles

Version 1 supports the following roles:

- ADMIN
- COLLABORATOR
- VIEWER

> **Note:** The repository owner is **not stored** in this table. Ownership is represented by the `owner_id` column in the `repositories` table. This avoids duplicate ownership information and keeps responsibilities clear.

---

# Indexes

| Index | Purpose |
|--------|---------|
| idx_repository_members_repository | Fetch all members of a repository |
| idx_repository_members_user | Fetch repositories a user collaborates on |
| idx_repository_members_role | Filter members by role |

---

# Business Rules

- A user cannot have multiple memberships in the same repository.
- Every member must have exactly one role.
- The repository owner is managed separately and is not duplicated as a member.
- Removing a membership immediately revokes repository access.

---

# Design Decisions

## Why use a separate table?

A direct many-to-many relationship only stores references.

A dedicated membership entity allows us to store:

- Role
- Join date
- Future invitation reference
- Future activity history
- Future custom permissions

This design scales much better as collaboration features grow.

---

## Why exclude the owner?

The owner already exists in the `repositories` table.

Storing the owner again in `repository_members` would duplicate information and could introduce inconsistencies.

Administrative actions that require ownership should always reference `repositories.owner_id`.

---

# Future Enhancements

The following fields are planned for future releases:

- invitation_id
- custom_permissions
- last_active_at
- removed_at
- removed_by

These additions can be introduced without redesigning the table.

---

# Example Record

| id | repository_id | user_id | role |
|----|---------------|---------|------|
| 8b6e... | a91d... | c24f... | COLLABORATOR |

---

# Conclusion

The `repository_members` table is the foundation of collaboration in ReviewForge.

It separates repository ownership from repository participation, making the permission model clear, scalable, and easy to extend in future versions.