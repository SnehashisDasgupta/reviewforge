# ReviewForge - Entity Lifecycle

## Purpose

This document defines the lifecycle of the primary entities within ReviewForge.

Each lifecycle describes the valid states an entity can transition through during its existence. These state transitions enforce business consistency and prevent invalid operations.

This document serves as the reference for:

- Domain logic
- Validation rules
- API behavior
- State management
- Future workflow automation

---

# Lifecycle Overview

| Entity | Lifecycle Defined |
|---------|-------------------|
| User | ✅ |
| Repository | ✅ |
| Repository Invitation | ✅ |
| Repository Member | ✅ |
| Review | ✅ |
| Documentation | ✅ |
| Notification | ✅ |

---

# User Lifecycle

```
REGISTERED
      │
      ▼
EMAIL_VERIFIED (Future)
      │
      ▼
ACTIVE
      │
      ├────────► SUSPENDED (Future)
      │
      └────────► DEACTIVATED
```

## Description

A user registers an account and becomes active after successful authentication. Future versions may introduce email verification and account suspension.

### Allowed Operations

| State | Allowed Operations |
|--------|--------------------|
| REGISTERED | Complete registration |
| ACTIVE | Full platform access |
| DEACTIVATED | No access |

---

# Repository Lifecycle

```
CREATED
    │
    ▼
ACTIVE
    │
    ├────────► ARCHIVED (Future)
    │
    └────────► DELETED
```

## Description

A repository becomes active immediately after creation. Future releases may support archiving before permanent deletion.

### Allowed Operations

| State | Allowed Operations |
|--------|--------------------|
| CREATED | Initial configuration |
| ACTIVE | Full repository operations |
| ARCHIVED | Read-only access (Future) |
| DELETED | No further operations |

---

# Repository Invitation Lifecycle

```
PENDING
   │
   ├────────► ACCEPTED
   │
   ├────────► REJECTED
   │
   ├────────► CANCELLED
   │
   └────────► EXPIRED
```

## Description

An invitation begins in the `PENDING` state and can transition to one of several terminal states.

### Business Rules

- Only pending invitations may be accepted.
- Expired invitations cannot be reactivated.
- Accepted invitations create a `RepositoryMember`.

---

# Repository Member Lifecycle

```
ACTIVE
   │
   ├────────► ROLE_UPDATED
   │
   └────────► REMOVED
```

## Description

A member becomes active after accepting an invitation. Their role may change during their membership, or they may be removed from the repository.

### Business Rules

- Every member has exactly one active role.
- Removed members lose repository access immediately.

---

# Review Lifecycle

```
CREATED
    │
    ▼
QUEUED
    │
    ▼
RUNNING
    │
    ├────────► COMPLETED
    │
    └────────► FAILED
```

## Description

Every AI review follows a predictable execution workflow.

### State Definitions

| State | Description |
|--------|-------------|
| CREATED | Review request received |
| QUEUED | Waiting for AI processing |
| RUNNING | AI analysis in progress |
| COMPLETED | Review finished successfully |
| FAILED | Review terminated due to an error |

### Business Rules

- A review cannot skip states.
- Only completed reviews generate results.
- Failed reviews may be retried by creating a new review.

---

# Documentation Lifecycle

```
GENERATING
      │
      ▼
AVAILABLE
      │
      ├────────► REGENERATING
      │               │
      │               ▼
      │          AVAILABLE
      │
      └────────► FAILED
```

## Description

Documentation is generated asynchronously and can be regenerated whenever repository content changes.

### Business Rules

- Documentation remains available until replaced by a newer version.
- Previous versions remain accessible.

---

# Notification Lifecycle

```
CREATED
    │
    ▼
DELIVERED
    │
    ├────────► READ
    │
    └────────► ARCHIVED (Future)
```

## Description

Notifications are created by system events and delivered to users.

### Business Rules

- Read status is irreversible.
- Archived notifications remain available for historical reference.

---

# Lifecycle Design Principles

The lifecycle model follows these principles:

- Every entity has clearly defined states.
- State transitions are explicit and predictable.
- Invalid transitions are rejected.
- Terminal states cannot transition back to active states unless explicitly supported.
- Lifecycle changes should be audited where applicable.

---

# Future Enhancements

Future versions of ReviewForge may extend these lifecycles with additional states, including:

- Email verification
- Soft deletion
- Repository visibility changes
- AI review cancellation
- Documentation publishing workflows
- Scheduled review execution
- Notification expiration

The current lifecycle definitions are intentionally minimal while remaining extensible.

---

# Conclusion

The lifecycle definitions establish the valid state transitions for the core entities of ReviewForge.

By defining these workflows before implementation, the platform maintains predictable behavior, simplifies validation, and provides a solid foundation for future workflow automation and event-driven processing.