# Permission Matrix

## Purpose

This document defines which repository roles are authorized to perform specific actions within ReviewForge.

It acts as the primary reference for implementing authorization rules in the backend.

---

# Repository Permissions

| Action | OWNER | ADMIN | COLLABORATOR | VIEWER |
|---------|:-----:|:-----:|:------------:|:------:|
| View repository | ✅ | ✅ | ✅ | ✅ |
| Update repository details | ✅ | ✅ | ❌ | ❌ |
| Delete repository | ✅ | ❌ | ❌ | ❌ |
| Transfer repository ownership | ✅ | ❌ | ❌ | ❌ |
| Invite members | ✅ | ✅ | ❌ | ❌ |
| Remove members | ✅ | ✅ | ❌ | ❌ |
| Change member role | ✅ | ✅* | ❌ | ❌ |
| View members | ✅ | ✅ | ✅ | ✅ |

> **\*** An ADMIN cannot modify the OWNER's role or assign/remove OWNER permissions.

---

# File Management Permissions

| Action | OWNER | ADMIN | COLLABORATOR | VIEWER |
|---------|:-----:|:-----:|:------------:|:------:|
| View files | ✅ | ✅ | ✅ | ✅ |
| Upload files | ✅ | ✅ | ✅ | ❌ |
| Update files | ✅ | ✅ | ✅ | ❌ |
| Delete files | ✅ | ✅ | ❌ | ❌ |
| Create directories | ✅ | ✅ | ✅ | ❌ |
| Rename directories | ✅ | ✅ | ✅ | ❌ |
| Delete directories | ✅ | ✅ | ❌ | ❌ |
| View file history | ✅ | ✅ | ✅ | ✅ |

---

# AI Features

| Action | OWNER | ADMIN | COLLABORATOR | VIEWER |
|---------|:-----:|:-----:|:------------:|:------:|
| Request AI review | ✅ | ✅ | ✅ | ❌ |
| View review results | ✅ | ✅ | ✅ | ✅ |
| Generate documentation | ✅ | ✅ | ✅ | ❌ |
| View documentation | ✅ | ✅ | ✅ | ✅ |

---

# Notification Permissions

| Action | OWNER | ADMIN | COLLABORATOR | VIEWER |
|---------|:-----:|:-----:|:------------:|:------:|
| View personal notifications | ✅ | ✅ | ✅ | ✅ |
| Mark notifications as read | ✅ | ✅ | ✅ | ✅ |

---

# General Rules

- Every repository has exactly one OWNER.
- A user may have different roles in different repositories.
- Authorization is always evaluated per repository.
- Higher roles inherit the permissions of lower roles.
- Repository membership is required before accessing repository resources.

---

# Future Permissions

The following permissions are planned for future releases:

- GitHub synchronization
- Branch management
- Pull request review
- Team management
- Organization administration
- API token management
- Webhook configuration

---

# Implementation Notes

The permission matrix is the source of truth for:

- Spring Security authorization
- Service-layer permission checks
- API documentation
- Integration tests
- Future RBAC enhancements

Any new feature that requires authorization must update this document before implementation.

---

# Conclusion

The permission matrix defines a consistent and centralized authorization strategy, ensuring that repository actions are governed by clear and predictable role-based access control (RBAC) rules.