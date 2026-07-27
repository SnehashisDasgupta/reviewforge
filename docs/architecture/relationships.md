# ReviewForge - Entity Relationships

## Purpose

This document defines the relationships between the core domain entities of ReviewForge.

It describes:

- Entity cardinality
- Ownership
- Relationship purpose
- Lifecycle dependencies
- Deletion behavior
- Future considerations

The goal is to ensure the domain model accurately reflects the business before designing the database schema.

---

# Relationship Overview

| Parent Entity | Child Entity | Cardinality |
|---------------|--------------|-------------|
| User | Repository | One-to-Many |
| User | RepositoryMember | One-to-Many |
| Repository | RepositoryMember | One-to-Many |
| Repository | RepositoryInvitation | One-to-Many |
| Repository | Directory | One-to-Many |
| Directory | Directory | One-to-Many (Self Relationship) |
| Directory | CodeFile | One-to-Many |
| CodeFile | FileVersion | One-to-Many |
| Repository | Review | One-to-Many |
| Review | ReviewResult | One-to-One |
| Review | ReviewIssue | One-to-Many |
| Repository | Documentation | One-to-Many |
| Documentation | DocumentationVersion | One-to-Many |
| User | Notification | One-to-Many |

---

# User → Repository

## Relationship

**One User owns many Repositories**

```
User (1)
    │
    ├────────► Repository (N)
```

### Description

A registered user can create multiple repositories.

Every repository must have exactly one owner.

### Ownership

User owns Repository.

### Business Rules

- Repository cannot exist without an owner.
- Repository ownership is unique.
- Repository names must be unique for the same owner.

### Delete Strategy

If a repository owner deletes a repository, all dependent repository data is removed according to the repository deletion policy.

---

# User ↔ Repository (Membership)

## Relationship

**Many-to-Many through RepositoryMember**

```
User
   │
   ├──── RepositoryMember ──── Repository
```

### Description

Users collaborate on repositories through the RepositoryMember entity.

This allows additional information to be stored, including:

- Role
- Joined Date
- Membership Status

### Why Not Direct Many-to-Many?

A direct many-to-many relationship cannot store business information.

RepositoryMember is therefore treated as a full domain entity rather than a simple join table.

### Business Rules

- A user cannot have duplicate memberships in the same repository.
- Every membership has exactly one role.
- Membership can be revoked.

---

# Repository → RepositoryInvitation

## Relationship

One Repository can have multiple invitations.

```
Repository
      │
      ├────────► RepositoryInvitation
```

### Business Rules

- Only authorized members can create invitations.
- Invitations expire after a configurable period.
- Accepted invitations become RepositoryMember records.

---

# Repository → Directory

## Relationship

One Repository contains many root directories.

```
Repository
      │
      ├────────► Directory
```

### Business Rules

- Every directory belongs to one repository.
- Root directories have no parent directory.
- Nested directories are allowed.

---

# Directory → Directory

## Relationship

Self-referencing One-to-Many

```
Directory
     │
     ├────► Directory
```

### Description

A directory may contain multiple child directories.

This models the hierarchical folder structure of software projects.

### Business Rules

- Circular references are not allowed.
- Parent directory may be null for root folders.

---

# Directory → CodeFile

## Relationship

One Directory contains many files.

```
Directory
      │
      ├────────► CodeFile
```

### Business Rules

- File names must be unique within the same directory.
- Every file belongs to exactly one directory.

---

# CodeFile → FileVersion

## Relationship

One file has many historical versions.

```
CodeFile
     │
     ├────────► FileVersion
```

### Business Rules

- Version history is immutable.
- Latest version is considered the active version.
- Previous versions cannot be modified.

---

# Repository → Review

## Relationship

One Repository can have many AI reviews.

```
Repository
      │
      ├────────► Review
```

### Business Rules

- Reviews are created on demand.
- Multiple reviews may exist for the same repository.
- Review history must be preserved.

---

# Review → ReviewResult

## Relationship

One-to-One

```
Review
   │
   └────────► ReviewResult
```

### Description

Each completed review produces exactly one review result.

### Business Rules

- A review result cannot exist without a review.
- A review may temporarily exist without a result while processing.

---

# Review → ReviewIssue

## Relationship

One Review contains multiple detected issues.

```
Review
   │
   ├────────► ReviewIssue
```

### Examples

- Bug
- Performance Issue
- Security Vulnerability
- Code Smell
- Maintainability Issue

### Business Rules

- Review issues are immutable once the review is completed.

---

# Repository → Documentation

## Relationship

One Repository can generate multiple documentation versions.

```
Repository
      │
      ├────────► Documentation
```

### Business Rules

- Documentation belongs to exactly one repository.
- Documentation can be regenerated after repository updates.

---

# Documentation → DocumentationVersion

## Relationship

One Documentation has multiple historical versions.

```
Documentation
       │
       ├────────► DocumentationVersion
```

### Business Rules

- Documentation history is preserved.
- Previous versions remain read-only.

---

# User → Notification

## Relationship

One User receives many notifications.

```
User
 │
 ├────────► Notification
```

### Examples

- Repository invitation
- Review completed
- Documentation generated
- Collaboration updates

### Business Rules

- Notifications belong to one user.
- Notifications can be marked as read.

---

# Relationship Diagram

```
User
├──────────── Owns ─────────────► Repository
│                                    │
│                                    ├────────► RepositoryMember ◄──────── User
│                                    ├────────► RepositoryInvitation
│                                    ├────────► Directory
│                                    │              │
│                                    │              ├────────► Directory
│                                    │              └────────► CodeFile
│                                    │                            │
│                                    │                            └────────► FileVersion
│                                    ├────────► Review
│                                    │              ├────────► ReviewResult
│                                    │              └────────► ReviewIssue
│                                    └────────► Documentation
│                                                   └────────► DocumentationVersion
│
└────────────────────────────────────────────► Notification
```

---

# Relationship Design Principles

The following principles guide relationship design:

- Every entity has a clear owner.
- Relationships reflect business concepts, not database convenience.
- Composition is preferred where child entities have no independent lifecycle.
- Relationship metadata is modeled through dedicated entities rather than simple join tables.
- Deletion behavior should preserve data integrity and avoid orphaned records.
- Future scalability has been considered when defining ownership boundaries.

---

# Conclusion

The relationships defined in this document establish the structural foundation of the ReviewForge domain model.

These relationships will directly influence:

- Entity design
- Database schema
- Foreign key constraints
- JPA mappings
- Aggregate boundaries
- API behavior

The next phase will refine these relationships into business invariants and lifecycle rules before we design the relational database.