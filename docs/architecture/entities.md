# ReviewForge - Domain Entities

## Purpose

This document defines the core domain entities of ReviewForge, their responsibilities, ownership, and lifecycle within the system.

At this stage, entities are described from a business perspective only. Implementation details such as database tables, JPA annotations, and API models are intentionally excluded.

The goal is to establish a clear understanding of each business concept before moving to database design and implementation.

---

# Entity Overview

| Entity | Purpose | Aggregate |
|----------|---------|-----------|
| User | Represents a registered developer using ReviewForge | User |
| Repository | Represents a software project | Repository |
| RepositoryMember | Represents a user's membership in a repository | Repository |
| RepositoryInvitation | Invitation to collaborate on a repository | Repository |
| Directory | Represents folders within a repository | Repository |
| CodeFile | Represents uploaded source code files | Repository |
| FileVersion | Maintains historical versions of a file | Repository |
| Review | Represents an AI review request | Review |
| ReviewResult | Stores AI review output | Review |
| ReviewIssue | Individual issues detected during review | Review |
| Documentation | Generated repository documentation | Documentation |
| DocumentationVersion | Version history of documentation | Documentation |
| Notification | System notifications | Notification |

---

# User

## Description

Represents a registered developer using the ReviewForge platform.

Every operation within the platform is performed on behalf of a user.

---

## Responsibilities

- Register an account
- Authenticate
- Own repositories
- Join repositories
- Request AI reviews
- Receive notifications
- Manage profile information

---

## Owns

- Repositories
- Review Requests
- Notifications

---

## Lifecycle

Registered
↓

Authenticated
↓

Active

↓

Deactivated (Future)

---

# Repository

## Description

Represents a software project managed within ReviewForge.

The repository acts as the central business object of the platform.

Almost every feature revolves around a repository.

---

## Responsibilities

- Store project metadata
- Maintain project files
- Manage members
- Store AI reviews
- Generate documentation
- Control repository permissions

---

## Owns

- Members
- Invitations
- Directories
- Files
- Reviews
- Documentation

---

## Lifecycle

Created
↓

Active

↓

Archived (Future)

↓

Deleted

---

# RepositoryMember

## Description

Represents a user's association with a repository.

This entity exists because the relationship between User and Repository contains additional information such as role and join date.

---

## Responsibilities

- Store repository role
- Store membership status
- Track joined date
- Support permission checks

---

## Lifecycle

Invited
↓

Accepted
↓

Active

↓

Removed

---

# RepositoryInvitation

## Description

Represents an invitation sent to another user to collaborate on a repository.

---

## Responsibilities

- Store invitation status
- Store invited user
- Store inviter
- Track expiration
- Track acceptance

---

## Lifecycle

Pending
↓

Accepted

OR

Rejected

OR

Expired

---

# Directory

## Description

Represents a logical folder inside a repository.

Directories preserve the original project structure.

---

## Responsibilities

- Organize files
- Maintain folder hierarchy
- Support nested directories

---

# CodeFile

## Description

Represents an uploaded source code or resource file.

The platform analyzes files during AI review and documentation generation.

---

## Responsibilities

- Store file metadata
- Maintain current version
- Associate with repository
- Associate with directory

---

# FileVersion

## Description

Maintains historical versions of a file.

This enables future features such as change tracking and version comparison.

---

## Responsibilities

- Preserve file history
- Record upload time
- Record uploader
- Support future diff analysis

---

# Review

## Description

Represents a single AI review request.

A review is created whenever a user requests analysis of a repository.

---

## Responsibilities

- Track review lifecycle
- Trigger AI processing
- Store review metadata
- Associate review results

---

## Lifecycle

Created
↓

Queued

↓

Running

↓

Completed

OR

Failed

---

# ReviewResult

## Description

Represents the overall outcome of an AI review.

---

## Responsibilities

- Store summary
- Store quality score
- Store recommendations
- Reference detected issues

---

# ReviewIssue

## Description

Represents an individual issue detected during an AI review.

Examples include:

- Bug
- Code Smell
- Performance Issue
- Security Risk
- Maintainability Concern

---

## Responsibilities

- Store issue details
- Store severity
- Store affected file
- Store suggested improvement

---

# Documentation

## Description

Represents generated engineering documentation for a repository.

---

## Responsibilities

- Store documentation metadata
- Maintain generated outputs
- Support regeneration

---

# DocumentationVersion

## Description

Represents historical versions of generated documentation.

---

## Responsibilities

- Maintain documentation history
- Support exports
- Track generation timestamps

---

# Notification

## Description

Represents an event communicated to a user.

Examples include:

- Repository invitation
- Review completed
- Documentation generated
- Repository updated

---

## Responsibilities

- Store notification content
- Store notification type
- Track read status

---

# Entity Ownership Summary

| Entity | Owned By |
|----------|----------|
| Repository | User |
| RepositoryMember | Repository |
| RepositoryInvitation | Repository |
| Directory | Repository |
| CodeFile | Repository |
| FileVersion | CodeFile |
| Review | Repository |
| ReviewResult | Review |
| ReviewIssue | Review |
| Documentation | Repository |
| DocumentationVersion | Documentation |
| Notification | User |

---

# Design Principles

The entity model follows these principles:

- Every entity has a single responsibility.
- Ownership is explicit.
- Business rules belong to the domain, not the database.
- Entities are independent of frameworks.
- Relationships should reflect real business concepts.
- Future scalability is considered from the beginning.

---

# Conclusion

These entities collectively represent the core business model of ReviewForge.

They form the basis for relationship modeling, database schema design, API contracts, and service-layer implementation in later phases.