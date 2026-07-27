# ReviewForge - Domain Model

## Purpose

This document defines the core business domain of ReviewForge. It identifies the primary business concepts, their responsibilities, relationships, and boundaries before any database tables or implementation details are introduced.

The goal is to ensure that the software is designed around the business problem rather than around database schemas or frameworks.

This document serves as the foundation for:

- Database Design
- Entity Design
- REST API Design
- Authorization Model
- Service Layer Design
- Future Microservice Boundaries

---

# Business Domain Overview

ReviewForge is an AI-powered software engineering platform that enables developers and teams to collaborate on source code, manage repositories, review code with AI assistance, and generate engineering documentation.

Unlike a traditional code hosting platform, ReviewForge focuses on improving the software development lifecycle by automating repetitive engineering tasks such as code reviews, documentation generation, code understanding, and quality analysis.

The platform combines repository management, collaboration, and AI capabilities into a single system.

---

# Core Business Capabilities

The platform is built around the following business capabilities.

## 1. Identity & Access Management

Responsible for managing users, authentication, authorization, and access control.

Responsibilities:

- User registration
- Login
- JWT authentication
- Role-based authorization
- Profile management

---

## 2. Repository Management

Responsible for managing software repositories.

Responsibilities:

- Create repositories
- Update repository information
- Delete repositories
- Repository settings
- Repository ownership

---

## 3. Collaboration

Allows multiple users to work on the same repository.

Responsibilities:

- Invite members
- Accept invitations
- Assign roles
- Remove members
- Manage permissions

---

## 4. File Management

Stores and organizes project files.

Responsibilities:

- Upload projects
- Maintain folder hierarchy
- Manage files
- Track file versions
- Retrieve source code

---

## 5. AI Review

Provides AI-powered engineering assistance.

Responsibilities:

- Review repositories
- Analyze code quality
- Detect code smells
- Suggest improvements
- Generate review reports

---

## 6. Documentation

Automatically generates engineering documentation.

Responsibilities:

- README generation
- Architecture documentation
- API documentation
- Folder documentation
- Business documentation

---

## 7. Repository Knowledge

Provides AI-powered understanding of repositories.

Responsibilities:

- Repository chat
- Semantic search
- Code explanation
- Business logic explanation

> **Note:** This capability is planned for future phases.

---

# Core Domain Entities

The following entities represent the primary business concepts within ReviewForge.

| Entity | Description |
|----------|-------------|
| User | A registered developer who uses the platform. |
| Repository | A software project managed inside ReviewForge. |
| RepositoryMember | Represents a user's membership and role within a repository. |
| RepositoryInvitation | Invitation sent to collaborate on a repository. |
| Directory | Represents folders inside a repository. |
| CodeFile | Represents a source code or resource file. |
| FileVersion | Maintains historical versions of uploaded files. |
| Review | Represents an AI review request for a repository. |
| ReviewResult | Stores the outcome of an AI review. |
| ReviewIssue | Individual issues detected during review. |
| Documentation | Generated documentation for a repository. |
| DocumentationVersion | Version history of generated documentation. |
| Notification | System notifications delivered to users. |

These entities describe the business domain only. Their database structure will be defined later during database design.

---

# Aggregate Roots

ReviewForge follows Domain-Driven Design (DDD) principles where appropriate.

Certain entities act as Aggregate Roots and are responsible for maintaining consistency within their boundaries.

## User Aggregate

Root Entity:

- User

Responsible for:

- User Profile
- Authentication Credentials
- Refresh Tokens (future)

---

## Repository Aggregate

Root Entity:

- Repository

Responsible for:

- Members
- Invitations
- Directories
- Files
- Repository Settings

A repository controls all operations related to its internal resources.

---

## Review Aggregate

Root Entity:

- Review

Responsible for:

- Review Results
- Review Issues
- Review Summary

Each review represents one complete AI analysis process.

---

## Documentation Aggregate

Root Entity:

- Documentation

Responsible for:

- Documentation Versions
- Export Information

---

# High-Level Relationships

The following relationships exist between the core entities.

```
User
│
├── Owns ─────────────► Repository
│
├── Member Of ───────► Repository
│
└── Creates ─────────► Review


Repository
│
├── Contains ───────► Directory
│
├── Contains ───────► CodeFile
│
├── Has ────────────► RepositoryMember
│
├── Has ────────────► Review
│
└── Generates ──────► Documentation


Review
│
├── Produces ───────► ReviewResult
│
└── Contains ───────► ReviewIssue


Documentation
│
└── Maintains ──────► DocumentationVersion
```

These relationships represent business ownership and interaction. Database relationships will be finalized during the schema design phase.

---

# Business Rules

The following business rules define important domain constraints.

## User

- Every user must have a unique email address.
- A user may own multiple repositories.
- A user may collaborate on multiple repositories.
- Users cannot access repositories without permission.

---

## Repository

- Every repository has exactly one owner.
- Repository names must be unique for the same owner.
- A repository may contain multiple members.
- A repository may contain multiple directories and files.
- Repository deletion follows the configured deletion strategy.

---

## Collaboration

- A user cannot have duplicate memberships within the same repository.
- Every member must have exactly one role.
- Invitations expire after a configurable period.
- Only authorized members may invite collaborators.

---

## Files

- Every file belongs to exactly one repository.
- File paths must be unique within a repository.
- Folder hierarchy must remain consistent.
- File version history should be preserved.

---

## Reviews

- Every review belongs to one repository.
- Every review is initiated by one user.
- Reviews progress through predefined lifecycle states.
- Completed review results are immutable.

---

## Documentation

- Documentation belongs to one repository.
- Multiple documentation versions may exist.
- Documentation can be regenerated after repository changes.

---

# Domain Boundaries

The domain is divided into independent business modules.

| Module | Primary Responsibility |
|---------|------------------------|
| Authentication | User authentication and security |
| User Management | User profile management |
| Repository Management | Repository lifecycle |
| Collaboration | Members, invitations, and roles |
| File Management | Project file storage |
| AI Review | AI-powered repository analysis |
| Documentation | Documentation generation |
| Notification | User notifications |
| Repository Knowledge | AI repository understanding |

These boundaries reduce coupling and provide clear ownership of business logic.

---

# Future Evolution

The current domain model is intentionally designed to support future capabilities without major redesign.

Planned enhancements include:

- GitHub synchronization
- Repository chat (RAG)
- Multi-agent AI workflows
- IDE integrations
- Multiple AI providers
- Organization workspaces
- Event-driven architecture
- Microservice decomposition

The modular domain structure allows these features to be introduced incrementally while preserving existing functionality.

---

# Out of Scope

The following concepts are intentionally excluded from the initial domain model:

- Billing and subscriptions
- Organization management
- Multi-tenancy
- Public repositories
- Mobile applications
- Kubernetes deployment
- Marketplace integrations

These features may be introduced in future releases if required.

---

# Conclusion

The ReviewForge domain model defines the core business concepts and their relationships independently of implementation details.

It provides a stable foundation for database design, API contracts, authorization rules, and service architecture. By modeling the business domain before writing code, ReviewForge remains easier to understand, extend, and maintain as it evolves into a production-grade AI-powered software engineering platform.