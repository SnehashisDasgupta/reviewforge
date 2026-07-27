# Entity Relationship Diagram (ERD)

## Overview

The ReviewForge database is organized into five major domains:

1. Identity & Authentication
2. Collaboration
3. Repository File System
4. AI Review
5. Documentation

---

# Entity Relationships

```text
User
 ├── owns ─────────────► Repository
 │
 ├── member of ────────► RepositoryMember
 │
 ├── invited by ───────► RepositoryInvitation
 │
 ├── requests ─────────► Review
 │
 ├── generates ────────► Documentation
 │
 └── receives ─────────► Notification

Repository
 ├── contains ─────────► Directory
 │
 ├── contains ─────────► CodeFile
 │
 ├── has ──────────────► Review
 │
 ├── has ──────────────► Documentation
 │
 ├── has ──────────────► RepositoryMember
 │
 └── has ──────────────► RepositoryInvitation

Directory
 ├── contains ─────────► Directory
 └── contains ─────────► CodeFile

CodeFile
 ├── has ──────────────► FileVersion
 └── has ──────────────► ReviewIssue

Review
 └── has ──────────────► ReviewResult

ReviewResult
 └── has ──────────────► ReviewIssue

Documentation
 └── has ──────────────► DocumentationVersion
```

---

# Domain Breakdown

## Identity

- Users

## Collaboration

- Repositories
- Repository Members
- Repository Invitations

## File System

- Directories
- Code Files
- File Versions

## AI Review

- Reviews
- Review Results
- Review Issues

## Documentation

- Documentations
- Documentation Versions

## Notifications

- Notifications

---

# Design Principles

- UUID primary keys
- Normalized schema
- Metadata separated from file storage
- Version history preserved
- Future-ready architecture

---

# Conclusion

The ERD provides a scalable foundation for ReviewForge while keeping domain boundaries clear and maintainable.