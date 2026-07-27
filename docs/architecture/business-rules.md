# ReviewForge - Business Rules

## Purpose

This document defines the core business rules of ReviewForge.

Business rules describe the conditions and constraints that must always be satisfied for the system to behave correctly. These rules are independent of programming language, framework, or database implementation.

Every feature developed in ReviewForge must respect these rules.

---

# User Management

## BR-001: Unique Email

Every registered user must have a unique email address.

**Reason**

Email is the primary identity used for authentication and communication.

---

## BR-002: Secure Password Storage

User passwords must never be stored in plain text.

Passwords must be securely hashed before persistence.

---

## BR-003: Authentication Required

Only authenticated users can access protected resources.

Public access is limited to authentication-related endpoints.

---

## BR-004: Account Ownership

Every action performed in the system must be attributable to an authenticated user.

---

# Repository Management

## BR-005: Single Repository Owner

Every repository must have exactly one owner.

Ownership cannot be shared.

---

## BR-006: Repository Name Uniqueness

Repository names must be unique for the same owner.

Different users may use identical repository names.

Example:

```
Alice
└── ecommerce

Bob
└── ecommerce
```

Valid.

```
Alice
├── ecommerce
└── ecommerce
```

Invalid.

---

## BR-007: Repository Access

Only users with repository membership may access repository resources.

---

## BR-008: Repository Deletion

Only the repository owner may permanently delete a repository.

Future versions may support repository archiving.

---

# Collaboration

## BR-009: Unique Membership

A user may have only one membership record within a repository.

Duplicate memberships are prohibited.

---

## BR-010: One Role Per Membership

Every repository member must have exactly one active role.

Examples:

- Owner
- Admin
- Collaborator
- Viewer

---

## BR-011: Invitation Acceptance

Only pending invitations may be accepted.

Expired, cancelled, or already accepted invitations cannot be reused.

---

## BR-012: Invitation Expiration

Repository invitations expire after the configured validity period.

Expired invitations become invalid automatically.

---

# File Management

## BR-013: Repository Ownership

Every file belongs to exactly one repository.

Files cannot exist independently.

---

## BR-014: Directory Ownership

Every directory belongs to exactly one repository.

---

## BR-015: Folder Hierarchy

Directories may contain child directories.

Circular references are prohibited.

---

## BR-016: Unique File Path

A repository cannot contain two files with the same absolute path.

Example:

```
src/service/UserService.java
```

must be unique within the repository.

---

## BR-017: File Version History

Historical file versions are immutable.

Only the latest version is considered editable.

---

# AI Review

## BR-018: Repository-Based Reviews

AI reviews are always performed against a repository.

A review cannot exist independently.

---

## BR-019: Review Ownership

Every review is initiated by one authenticated user.

---

## BR-020: Review Lifecycle

Every review progresses through the following states:

```
PENDING
    ↓
QUEUED
    ↓
RUNNING
    ↓
COMPLETED
```

or

```
RUNNING
    ↓
FAILED
```

State transitions must follow this lifecycle.

---

## BR-021: Immutable Results

Completed review results cannot be modified.

If another review is required, a new review must be created.

---

# Documentation

## BR-022: Repository Association

Documentation always belongs to one repository.

---

## BR-023: Regeneration

Documentation may be regenerated whenever repository contents change.

Previous versions should remain available for historical reference.

---

# Notifications

## BR-024: User Ownership

Notifications always belong to one user.

---

## BR-025: Read Status

Notifications may be marked as read but should not be modified.

---

# Authorization

## BR-026: Permission-Based Actions

Operations are authorized based on repository membership and assigned role.

Examples include:

- Creating repositories
- Inviting members
- Uploading files
- Requesting AI reviews
- Deleting repositories

Detailed permissions are defined separately in the Permission Matrix.

---

# Auditing

## BR-027: Track Ownership

Every business entity should record who created it and when.

Where applicable, entities should also record:

- Last modified by
- Last modified at

---

## BR-028: Soft Delete Strategy

Business-critical entities should support logical deletion where appropriate.

Examples include:

- Users
- Repositories

Child entities may be removed automatically depending on lifecycle ownership.

---

# Future Rules

The following rules are reserved for future releases:

- GitHub synchronization
- Organization workspaces
- Repository visibility
- Multi-tenancy
- Billing
- AI usage quotas
- API rate limiting

These rules are intentionally excluded from Version 1.

---

# Conclusion

The business rules defined in this document represent the core behavioral constraints of ReviewForge.

They serve as the source of truth for validation, authorization, persistence, and testing, ensuring that business requirements remain consistent regardless of implementation details.