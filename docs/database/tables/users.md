# Users Table Design

## Purpose

The `users` table stores information about every registered user of ReviewForge.

It serves as the central identity table for authentication, authorization, repository ownership, collaboration, and future platform features.

Every authenticated action within ReviewForge is performed on behalf of a user.

---

# Responsibilities

The `users` table is responsible for:

- Storing user account information
- Supporting authentication
- Providing identity for repository ownership
- Providing identity for collaboration
- Supporting auditing
- Supporting future profile management

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| repositories | One User → Many Repositories |
| repository_members | One User → Many Memberships |
| repository_invitations | One User → Many Invitations (Future) |
| reviews | One User → Many Reviews |
| notifications | One User → Many Notifications |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| full_name | VARCHAR(100) | ❌ | User's display name |
| email | VARCHAR(255) | ❌ | Unique email address |
| password_hash | VARCHAR(255) | ❌ | BCrypt/Argon2 hashed password |
| account_status | VARCHAR(30) | ❌ | Current account status |
| created_at | TIMESTAMP WITH TIME ZONE | ❌ | Account creation time |
| updated_at | TIMESTAMP WITH TIME ZONE | ❌ | Last update time |

---

# Primary Key

```text
PRIMARY KEY (id)
```

UUID is used to support distributed systems and future microservice decomposition.

---

# Unique Constraints

```text
UNIQUE (email)
```

Business Rule:

- No two users may register with the same email address.

---

# Check Constraints

The following values are valid for `account_status`:

- ACTIVE
- DEACTIVATED

Future versions may introduce:

- PENDING_VERIFICATION
- SUSPENDED
- LOCKED

---

# Indexes

| Index | Purpose |
|--------|---------|
| idx_users_email | Fast login and user lookup |
| idx_users_status | Filter users by account status |

---

# Business Rules

- Every user must have a unique email.
- Passwords must never be stored in plain text.
- A user may own multiple repositories.
- A user may belong to multiple repositories.
- Deactivating a user does not automatically delete owned repositories.

---

# Future Enhancements

The following fields are intentionally excluded from Version 1:

- profile_picture
- bio
- timezone
- preferred_language
- last_login_at
- email_verified
- two_factor_enabled
- oauth_provider
- deleted_at

These can be added in future schema migrations without affecting the current design.

---

# Example Record

| id | full_name | email | account_status |
|----|-----------|-------|----------------|
| 550e8400-e29b-41d4-a716-446655440000 | Alice Johnson | alice@example.com | ACTIVE |

---

# Design Decisions

### Why UUID instead of BIGINT?

- Better support for distributed systems
- Prevents predictable identifiers
- Easier future migration to microservices

---

### Why `password_hash` instead of `password`?

The database must never store raw passwords.

Only securely hashed passwords are persisted.

---

### Why `account_status`?

Using a status column avoids deleting user accounts and allows future features such as account suspension or verification without changing the schema.

---

# Conclusion

The `users` table acts as the identity foundation of ReviewForge. It is intentionally small, secure, and extensible, providing a stable base for authentication, authorization, and ownership throughout the platform.