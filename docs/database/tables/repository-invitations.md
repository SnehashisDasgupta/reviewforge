# Repository Invitations Table Design

## Purpose

The `repository_invitations` table manages the process of inviting users to collaborate on repositories.

Instead of granting access directly, ReviewForge uses an invitation workflow. This provides a secure and auditable way to onboard collaborators while allowing invitations to be accepted, rejected, cancelled, or expire.

---

# Responsibilities

The `repository_invitations` table is responsible for:

- Managing repository invitations
- Tracking invitation status
- Recording who sent the invitation
- Defining the role to be assigned upon acceptance
- Supporting invitation expiration

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| repositories | Many Invitations → One Repository |
| users | Many Invitations → One Invited User |
| users | Many Invitations → One Invited By User |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| repository_id | UUID | ❌ | Target repository |
| invited_user_id | UUID | ❌ | User being invited |
| invited_by_user_id | UUID | ❌ | User who sent the invitation |
| role | VARCHAR(30) | ❌ | Role granted after acceptance |
| status | VARCHAR(30) | ❌ | Invitation status |
| expires_at | TIMESTAMP WITH TIME ZONE | ❌ | Invitation expiry time |
| responded_at | TIMESTAMP WITH TIME ZONE | ✅ | Acceptance or rejection time |
| created_at | TIMESTAMP WITH TIME ZONE | ❌ | Invitation creation time |
| updated_at | TIMESTAMP WITH TIME ZONE | ❌ | Last modification time |

---

# Primary Key

```text
PRIMARY KEY (id)
```

---

# Foreign Keys

```text
repository_id REFERENCES repositories(id)

invited_user_id REFERENCES users(id)

invited_by_user_id REFERENCES users(id)
```

---

# Unique Constraints

Only one **active (pending)** invitation should exist for the same user and repository at a time.

> This rule will primarily be enforced by the application layer. If needed, it can later be optimized using a partial unique index in PostgreSQL.

---

# Allowed Roles

- ADMIN
- COLLABORATOR
- VIEWER

---

# Allowed Status Values

- PENDING
- ACCEPTED
- REJECTED
- CANCELLED
- EXPIRED

---

# Indexes

| Index | Purpose |
|--------|---------|
| idx_repository_invitations_repository | Find invitations for a repository |
| idx_repository_invitations_invited_user | Find invitations received by a user |
| idx_repository_invitations_status | Filter by invitation status |
| idx_repository_invitations_expires_at | Clean up expired invitations |

---

# Business Rules

- Only authorized repository users may send invitations.
- The invited user must already have an account.
- A user cannot invite themselves.
- Accepting an invitation creates a `repository_members` record.
- Expired or cancelled invitations cannot be accepted.
- Once accepted, the invitation becomes immutable.

---

# Design Decisions

## Why store `invited_by_user_id`?

Invitation history should always show who granted access to the repository. This improves traceability and future audit capabilities.

---

## Why use `expires_at`?

Invitations should not remain valid indefinitely. Expiration reduces the risk of old invitations being used unintentionally and supports future automated cleanup jobs.

---

# Future Enhancements

The following fields may be added in future releases:

- invitation_message
- reminder_sent_at
- accepted_ip_address
- invitation_token
- audit_log_reference

---

# Example Record

| id | repository_id | invited_user_id | role | status |
|----|---------------|-----------------|------|--------|
| 92ab... | a91d... | c24f... | COLLABORATOR | PENDING |

---

# Conclusion

The `repository_invitations` table provides a secure and extensible collaboration workflow. It separates invitation management from membership management, making access control easier to maintain and audit as the platform grows.