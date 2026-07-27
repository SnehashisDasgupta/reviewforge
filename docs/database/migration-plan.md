# Database Migration Plan

## Purpose

This document defines the order in which database tables should be created using Flyway migrations.

The migration sequence respects foreign key dependencies and ensures successful database initialization.

---

# Migration Order

## Phase 1 - Core Identity

1. users

---

## Phase 2 - Repository Management

2. repositories

3. repository_members

4. repository_invitations

---

## Phase 3 - File System

5. directories

6. code_files

7. file_versions

---

## Phase 4 - AI Review

8. reviews

9. review_results

10. review_issues

---

## Phase 5 - Documentation

11. documentations

12. documentation_versions

---

## Phase 6 - Notifications

13. notifications

---

# Future Migrations

Future Flyway migrations may introduce:

- Soft delete support
- Audit fields
- GitHub synchronization
- Organizations
- AI model tracking
- Vector embeddings
- Repository chat
- Usage analytics

---

# Migration Naming Convention

```text
V1__create_users.sql

V2__create_repositories.sql

V3__create_repository_members.sql

...
```

Each migration should contain a single logical database change to keep migrations easy to review, test, and roll back.

---

# Rollback Strategy

Flyway Community Edition does not support automatic rollback migrations.

Any rollback must be handled through a new forward migration that safely reverses the previous change.

---

# Conclusion

Following a structured migration plan ensures that ReviewForge's database evolves safely, predictably, and consistently across all environments.