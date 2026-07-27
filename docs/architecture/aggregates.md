# ReviewForge - Aggregate Design

## Purpose

This document defines the aggregate boundaries of the ReviewForge domain model.

An aggregate is a cluster of closely related domain objects that are treated as a single consistency boundary. Each aggregate has one Aggregate Root responsible for enforcing business rules and maintaining the integrity of its child entities.

Aggregate boundaries help define:

- Transaction boundaries
- Ownership
- Consistency rules
- Service responsibilities
- Future microservice boundaries

---

# Aggregate Overview

| Aggregate | Root Entity | Child Entities |
|-----------|-------------|----------------|
| User | User | Notification |
| Repository | Repository | RepositoryMember, RepositoryInvitation, Directory, CodeFile |
| File | CodeFile | FileVersion |
| Review | Review | ReviewResult, ReviewIssue |
| Documentation | Documentation | DocumentationVersion |

---

# User Aggregate

## Aggregate Root

**User**

### Responsibilities

- Manage account information
- Authenticate users
- Own repositories
- Receive notifications

### Child Entities

- Notification

### Business Rules

- Every user has a unique email.
- A user may own multiple repositories.
- A user may belong to multiple repositories.
- Notifications cannot exist without a user.

---

# Repository Aggregate

## Aggregate Root

**Repository**

The Repository is the central aggregate of ReviewForge.

Most business operations originate from this aggregate.

### Responsibilities

- Manage repository metadata
- Manage repository members
- Manage invitations
- Manage project structure
- Store uploaded files
- Control repository access

### Child Entities

- RepositoryMember
- RepositoryInvitation
- Directory
- CodeFile

### Business Rules

- Repository always has one owner.
- Repository controls membership.
- Repository controls permissions.
- Repository controls file organization.
- Child entities cannot exist independently.

---

# File Aggregate

## Aggregate Root

**CodeFile**

Although files belong to a repository, version history forms its own consistency boundary.

### Responsibilities

- Maintain current file
- Maintain version history
- Preserve file metadata

### Child Entities

- FileVersion

### Business Rules

- Every version belongs to one file.
- Previous versions are immutable.
- Latest version is the active version.

---

# Review Aggregate

## Aggregate Root

**Review**

Represents one AI analysis request.

### Responsibilities

- Manage review lifecycle
- Store review metadata
- Produce review output

### Child Entities

- ReviewResult
- ReviewIssue

### Business Rules

- One review produces one result.
- A review may contain many issues.
- Completed reviews cannot be modified.
- New analyses create new review records.

---

# Documentation Aggregate

## Aggregate Root

**Documentation**

Represents generated engineering documentation.

### Responsibilities

- Maintain generated documents
- Manage documentation history
- Support regeneration

### Child Entities

- DocumentationVersion

### Business Rules

- Documentation belongs to one repository.
- Previous versions remain available.
- New generations create new versions.

---

# Aggregate Interaction

Aggregates communicate through their root entities.

```
User
 │
 ├────────► Repository
 │              │
 │              ├────────► CodeFile
 │              │              │
 │              │              └────────► FileVersion
 │              │
 │              ├────────► Review
 │              │              ├────────► ReviewResult
 │              │              └────────► ReviewIssue
 │              │
 │              └────────► Documentation
 │                             └────────► DocumentationVersion
 │
 └────────► Notification
```

---

# Consistency Boundaries

Business rules are enforced only within an aggregate.

Examples:

### Repository Aggregate

Valid operations:

- Add member
- Remove member
- Upload file
- Rename repository

These operations maintain consistency inside the Repository aggregate.

---

### Review Aggregate

Valid operations:

- Create review
- Update review status
- Save review result
- Save detected issues

All review-related changes remain inside the Review aggregate.

---

# Cross-Aggregate Communication

Aggregates should not modify each other's internal state directly.

Examples:

- A Review references a Repository but does not modify repository metadata.
- A Notification references a User but does not update user information.
- Documentation references a Repository but does not change repository settings.

Communication should occur through application services.

---

# Transaction Boundaries

Each aggregate should normally be modified within a single transaction.

Examples:

✅ Create Repository

- Repository
- Owner assignment

Single transaction.

---

✅ Invite Member

- RepositoryInvitation

Single transaction.

---

✅ Complete AI Review

- Review
- ReviewResult
- ReviewIssue

Single transaction.

---

# Future Microservice Mapping

The aggregate boundaries naturally align with future service boundaries.

| Aggregate | Future Service |
|-----------|----------------|
| User | Authentication Service |
| Repository | Repository Service |
| Review | AI Review Service |
| Documentation | Documentation Service |
| Notification | Notification Service |

This allows ReviewForge to evolve from a modular monolith to a distributed architecture with minimal redesign.

---

# Design Principles

The aggregate model follows these principles:

- Every aggregate has one root entity.
- Child entities are managed only through their root.
- Business invariants are enforced within aggregate boundaries.
- Aggregates communicate through references, not direct ownership.
- Aggregate boundaries minimize coupling and support future scalability.

---

# Conclusion

The aggregate design establishes clear ownership, consistency boundaries, and transactional responsibilities within the ReviewForge domain.

It provides a stable foundation for implementing business logic while supporting future architectural evolution.