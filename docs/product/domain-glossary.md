# ReviewForge - Domain Glossary

## Purpose

The Domain Glossary defines the common business terms used throughout ReviewForge. It establishes a shared language for developers, architects, product managers, testers, and future contributors so that everyone understands the system in the same way.

This document follows the **Domain-Driven Design (DDD)** principle of **Ubiquitous Language**, ensuring that every business concept has one clear and consistent meaning across documentation, APIs, database design, source code, and architecture discussions.

> **Note:** This document focuses on business concepts only. It does **not** describe implementation details such as database tables, Java classes, or API endpoints.

---

# Business Terms

## User

**Definition**

A **User** is a registered person who can access and use ReviewForge.

Every user can create repositories, collaborate with other users, request AI-powered code reviews, and manage projects based on their assigned permissions.

**Responsibilities**

- Register and authenticate.
- Create and manage repositories.
- Collaborate with other users.
- Request AI-powered code reviews.
- View generated documentation and review history.

---

## Repository

**Definition**

A **Repository** is the primary workspace within ReviewForge. It represents a software project and acts as the central container for all project-related resources.

A repository contains source code, folders, collaborators, AI reviews, generated documentation, and future project artifacts.

Almost every operation performed in ReviewForge happens within the context of a repository.

**Responsibilities**

- Store project files and folders.
- Manage collaborators and permissions.
- Maintain review history.
- Store generated documentation.
- Serve as the central workspace for software development.

---

## Repository Member

**Definition**

A **Repository Member** represents a user's participation in a repository.

Each member is assigned a specific role that determines the actions they are allowed to perform within that repository.

Examples of roles include:

- Owner
- Admin *(Future)*
- Collaborator
- Viewer

**Responsibilities**

- Access repositories based on assigned permissions.
- Collaborate with other team members.
- Perform repository actions allowed by their role.

---

## Directory

**Definition**

A **Directory** represents a folder within a repository.

Directories help organize project files into a hierarchical structure similar to a local file system.

They allow developers to upload and manage complete software projects while preserving the original folder structure.

**Responsibilities**

- Organize project files.
- Maintain folder hierarchy.
- Improve project navigation.

---

## Code File

**Definition**

A **Code File** represents any file stored within a repository.

Although primarily intended for source code, it may also include configuration files, documentation files, build files, scripts, and other project resources.

Examples include:

- `.java`
- `.py`
- `.js`
- `.ts`
- `.xml`
- `.yaml`
- `.json`
- `README.md`

**Responsibilities**

- Store project content.
- Support AI analysis.
- Support documentation generation.
- Maintain version history.

---

## File Version

**Definition**

A **File Version** represents a historical snapshot of a code file.

Version history enables users to track changes, compare previous revisions, and support future versioning features.

**Responsibilities**

- Preserve file history.
- Enable change tracking.
- Support future rollback capabilities.

---

## Review

**Definition**

A **Review** represents a request to analyze one or more project files using Artificial Intelligence.

A review records the analysis request itself rather than the AI-generated output.

**Responsibilities**

- Track AI analysis requests.
- Maintain review history.
- Support asynchronous processing.
- Record review status.

---

## Review Result

**Definition**

A **Review Result** contains the output generated after an AI review has been completed.

A review result may include:

- Code quality feedback
- Optimization suggestions
- Security observations
- Code smells
- Best practice recommendations
- Performance improvements

A single review may generate one or more review results.

**Responsibilities**

- Store AI-generated analysis.
- Present recommendations to users.
- Maintain historical review reports.

---

## Documentation

**Definition**

Documentation represents technical information generated either manually or automatically by ReviewForge.

Documentation helps developers understand a project without manually reading every source file.

Examples include:

- Project overview
- README
- API documentation
- Architecture documentation
- Folder documentation
- Class documentation
- Database documentation

**Responsibilities**

- Improve project understanding.
- Reduce onboarding time.
- Keep project knowledge centralized.

---

## Notification

**Definition**

A **Notification** is a system-generated message that informs users about important events occurring within ReviewForge.

Examples include:

- Repository invitation received.
- AI review completed.
- Documentation generated.
- Collaboration request accepted.
- Future system alerts.

**Responsibilities**

- Notify users of important events.
- Improve communication.
- Increase user awareness.

---

## Invitation

**Definition**

An **Invitation** represents a request sent by a repository owner or administrator inviting another user to join a repository.

An invitation remains pending until it is accepted or rejected.

**Responsibilities**

- Invite collaborators.
- Manage repository membership.
- Track invitation status.

---

# Naming Guidelines

To maintain consistency throughout the project, the following naming conventions should always be followed.

## Documentation

- Use the business terms defined in this document.
- Avoid introducing alternative names for the same concept.
- Keep terminology consistent across all engineering documents.

---

## REST APIs

API resource names should match the business terminology.

**Examples**

- `/users`
- `/repositories`
- `/reviews`
- `/invitations`
- `/notifications`

Avoid abbreviations or inconsistent naming.

---

## Database Design

Database tables should closely reflect the domain terminology.

**Examples**

- `users`
- `repositories`
- `repository_members`
- `directories`
- `code_files`
- `file_versions`
- `reviews`
- `review_results`
- `notifications`
- `invitations`

---

## Source Code

Java class names should follow the domain language.

**Examples**

- `User`
- `Repository`
- `RepositoryMember`
- `Directory`
- `CodeFile`
- `FileVersion`
- `Review`
- `ReviewResult`
- `Notification`
- `Invitation`

---

## Architecture Discussions

When discussing ReviewForge, always use the terms defined in this glossary.

For example:

- Say **Repository**, not *Project*.
- Say **Repository Member**, not *Collaborator Entity*.
- Say **Review**, not *AI Request*.
- Say **Review Result**, not *AI Response*.

Maintaining a consistent vocabulary reduces ambiguity, improves communication, and makes the system easier to understand and evolve.

---

# Conclusion

The Domain Glossary serves as the single source of truth for the business language used throughout ReviewForge. Every future design decision—including database modeling, REST API design, package structure, and microservice boundaries—should align with the terminology defined in this document.

As ReviewForge evolves, this glossary should be updated to include new business concepts while preserving consistency across the entire platform.