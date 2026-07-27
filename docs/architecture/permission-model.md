# Permission Model

## Purpose

This document defines the authorization model used in ReviewForge.

It specifies the different roles available within the system, their responsibilities, and how permissions are assigned. A well-defined permission model ensures that users can only perform actions they are authorized to perform, improving security, maintainability, and scalability.

This document serves as the single source of truth for authorization throughout the application.

---

# Authorization Philosophy

ReviewForge follows the **Principle of Least Privilege (PoLP)**.

A user is granted only the minimum permissions required to perform their responsibilities.

This approach:

- Improves application security
- Reduces accidental modifications
- Prevents unauthorized access
- Simplifies permission management

---

# Types of Roles

ReviewForge defines two categories of roles.

## 1. Platform Roles

Platform roles determine permissions across the entire ReviewForge application.

These roles are independent of any specific repository.

### Version 1

| Role | Description |
|------|-------------|
| USER | A registered user who can create repositories, join repositories, and use ReviewForge features. |

### Future Roles

| Role | Description |
|------|-------------|
| SYSTEM_ADMIN | Manages the entire platform. |
| SUPPORT_ENGINEER | Assists users with support-related operations. |

---

## 2. Repository Roles

Repository roles determine what a user can do within a specific repository.

Each repository maintains its own membership and permissions.

| Role | Description |
|------|-------------|
| OWNER | Creator of the repository. Has full control over repository settings and ownership. |
| ADMIN | Helps manage the repository, members, files, and AI features. Cannot transfer or delete repository ownership. |
| COLLABORATOR | Can contribute to the repository by uploading files and requesting AI operations. |
| VIEWER | Read-only access to repository resources. |

---

# Role Hierarchy

```text
OWNER
│
├── ADMIN
│
├── COLLABORATOR
│
└── VIEWER
```

Permissions flow downward.

For example:

- OWNER inherits all ADMIN permissions.
- ADMIN inherits all COLLABORATOR permissions.
- COLLABORATOR inherits all VIEWER permissions.

---

# Platform Role vs Repository Role

Example:

A user registers in ReviewForge.

Platform Role:

```text
USER
```

Inside Repository A:

```text
OWNER
```

Inside Repository B:

```text
VIEWER
```

Inside Repository C:

```text
COLLABORATOR
```

A user's repository role may differ for every repository they belong to.

---

# Repository Ownership

Every repository has exactly one OWNER.

The OWNER:

- Creates the repository
- Can transfer ownership
- Can delete the repository
- Can manage all repository settings

Ownership is stored in:

```text
repositories.owner_id
```

The OWNER is not duplicated in the `repository_members` table to avoid redundant data.

---

# Permission Inheritance

Higher roles automatically inherit permissions from lower roles.

Example:

OWNER

inherits

- ADMIN
- COLLABORATOR
- VIEWER

This reduces duplicate permission definitions and simplifies authorization checks.

---

# Authorization Principles

ReviewForge follows these principles:

- Authentication always occurs before authorization.
- Repository permissions are checked for every protected repository operation.
- Resource ownership is validated in the service layer.
- Business rules are enforced independently of UI restrictions.
- Database constraints provide an additional layer of protection.

---

# Future Enhancements

Future versions may introduce:

- Organization-level roles
- Team-based permissions
- Custom repository roles
- Temporary access
- Fine-grained permissions
- Role expiration
- Audit logging for permission changes

---

# Conclusion

The permission model provides a clear separation between platform-wide permissions and repository-specific permissions. This design keeps the authorization system simple for Version 1 while remaining scalable for future enterprise features.