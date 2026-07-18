# ReviewForge - Use Cases

## Purpose

This document describes how users interact with ReviewForge to accomplish business goals.

Unlike User Stories, which describe *what* users want, Use Cases describe *how* those interactions take place step by step.

These use cases serve as the foundation for:

- REST API Design
- Database Design
- Sequence Diagrams
- UI Development
- Test Cases
- Sprint Planning

---

# Use Case Structure

Each use case contains:

- Goal
- Primary Actor
- Preconditions
- Trigger
- Main Success Flow
- Alternative Flow(s)
- Exception Flow(s)
- Postconditions

---

# UC-001 – User Registration

## Goal

Allow a new user to create a ReviewForge account.

## Primary Actor

Guest User

## Preconditions

- User is not logged in.
- Email is not already registered.

## Trigger

User clicks **Register**.

## Main Success Flow

1. User enters registration details.
2. System validates the input.
3. System checks whether the email already exists.
4. System securely stores the user account.
5. System confirms successful registration.

## Alternative Flows

- User provides optional profile information.

## Exception Flows

- Email already exists.
- Invalid email format.
- Weak password.

## Postconditions

A new user account exists in the system.

---

# UC-002 – User Login

## Goal

Allow an existing user to access ReviewForge.

## Primary Actor

Registered User

## Preconditions

- User account exists.

## Trigger

User clicks **Login**.

## Main Success Flow

1. User enters email and password.
2. System validates credentials.
3. Authentication succeeds.
4. System creates an authenticated session.
5. User is redirected to the dashboard.

## Exception Flows

- Invalid credentials.
- Locked account.
- Disabled account.

## Postconditions

User is authenticated.

---

# UC-003 – Create Repository

## Goal

Allow a user to create a new software repository.

## Primary Actor

Authenticated User

## Preconditions

- User is logged in.

## Trigger

User clicks **Create Repository**.

## Main Success Flow

1. User enters repository information.
2. System validates the data.
3. Repository is created.
4. User becomes Repository Owner.
5. Repository appears in the dashboard.

## Exception Flows

- Invalid repository name.
- Duplicate repository (if restricted).

## Postconditions

Repository is available for future operations.

---

# UC-004 – Upload Project

## Goal

Allow users to upload an entire software project.

## Primary Actor

Repository Member

## Preconditions

- Repository exists.
- User has upload permission.

## Trigger

User uploads a project.

## Main Success Flow

1. User selects a project.
2. System receives uploaded files.
3. Folder hierarchy is preserved.
4. Files are stored.
5. Repository structure is updated.

## Exception Flows

- Upload interrupted.
- Unsupported file.
- Storage failure.

## Postconditions

Repository contains uploaded files.

---

# UC-005 – Invite Collaborator

## Goal

Allow repository owners to invite other users.

## Primary Actor

Repository Owner

## Preconditions

- Repository exists.
- Invited user exists.

## Trigger

Owner clicks **Invite Member**.

## Main Success Flow

1. Owner enters user email.
2. System verifies the user.
3. Owner selects a role.
4. Invitation is created.
5. Notification is sent.

## Exception Flows

- User not found.
- User already a member.
- Duplicate invitation.

## Postconditions

Invitation is pending.

---

# UC-006 – Accept Invitation

## Goal

Allow invited users to join repositories.

## Primary Actor

Invited User

## Preconditions

- Invitation exists.

## Trigger

User accepts invitation.

## Main Success Flow

1. User opens invitation.
2. User accepts.
3. Membership is created.
4. Repository appears in dashboard.

## Exception Flows

- Invitation expired.
- Invitation revoked.

## Postconditions

User becomes a repository member.

---

# UC-007 – Request AI Review

## Goal

Generate an AI review for a repository.

## Primary Actor

Repository Member

## Preconditions

- Repository contains uploaded files.
- User has permission to request reviews.

## Trigger

User clicks **Generate AI Review**.

## Main Success Flow

1. User requests a review.
2. System creates a Review request.
3. AI analyzes the repository.
4. AI generates recommendations.
5. Review results are stored.
6. User receives a notification.

## Exception Flows

- AI service unavailable.
- Review timeout.
- Repository contains no files.

## Postconditions

Review results are available.

---

# UC-008 – View AI Review

## Goal

Allow users to view AI-generated review results.

## Primary Actor

Repository Member

## Preconditions

- Review has completed.

## Trigger

User opens review history.

## Main Success Flow

1. User selects a review.
2. System retrieves review results.
3. Recommendations are displayed.

## Exception Flows

- Review not completed.
- Review deleted.

## Postconditions

User understands code quality findings.

---

# UC-009 – Generate Documentation

## Goal

Generate technical documentation for a repository.

## Primary Actor

Repository Member

## Preconditions

- Repository contains project files.

## Trigger

User clicks **Generate Documentation**.

## Main Success Flow

1. User requests documentation.
2. AI analyzes the repository.
3. Documentation is generated.
4. Documentation is stored.
5. User views or exports it.

## Exception Flows

- AI generation failed.
- Repository empty.

## Postconditions

Documentation becomes available.

---

# UC-010 – Repository Chat (Future)

## Goal

Allow developers to ask natural language questions about their repository.

## Primary Actor

Repository Member

## Preconditions

- Repository has been indexed.
- AI knowledge base exists.

## Trigger

User submits a question.

## Main Success Flow

1. User asks a question.
2. AI searches repository knowledge.
3. AI generates an answer.
4. References to source files are displayed.

## Exception Flows

- Repository not indexed.
- AI unavailable.

## Postconditions

User gains a better understanding of the codebase.

---

# Traceability

Each use case is linked to:

- User Stories
- Functional Requirements
- REST APIs
- Database Entities
- Sequence Diagrams
- Test Cases

This ensures every technical implementation can be traced back to a business interaction.

---

# Conclusion

Use Cases bridge the gap between business requirements and technical implementation. They provide a shared understanding of user interactions and serve as a reference throughout development, testing, and maintenance.

As ReviewForge evolves, this document should be updated to reflect new user workflows and system capabilities.