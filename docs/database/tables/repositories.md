# Repositories Table Design

## Purpose

The `repositories` table stores software projects managed within ReviewForge.

A repository is the central business entity of the platform. It acts as the container for source code, project structure, collaborators, AI reviews, documentation, and future engineering insights.

Every repository belongs to exactly one owner and serves as the primary workspace for development and collaboration.

---

# Responsibilities

The `repositories` table is responsible for:

- Storing repository metadata
- Managing repository ownership
- Defining repository visibility
- Providing the root container for project resources
- Supporting collaboration
- Supporting AI-powered analysis

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| users | Many Repositories → One User (Owner) |
| repository_members | One Repository → Many Members |
| repository_invitations | One Repository → Many Invitations |
| directories | One Repository → Many Directories |
| reviews | One Repository → Many Reviews |
| documentations | One Repository → Many Documentation Records |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| owner_id | UUID | ❌ | Repository owner |
| name | VARCHAR(100) | ❌ | Repository name |
| description | TEXT | ✅ | Repository description |
| visibility | VARCHAR(20) | ❌ | Repository visibility |
| default_branch | VARCHAR(50) | ❌ | Default branch (usually `main`) |
| created_at | TIMESTAMP WITH TIME ZONE | ❌ | Creation timestamp |
| updated_at | TIMESTAMP WITH TIME ZONE | ❌ | Last modification timestamp |

---

# Primary Key

```text
PRIMARY KEY (id)
```

---

# Foreign Keys

```text
owner_id
    REFERENCES users(id)
```

A repository must always have a valid owner.

---

# Unique Constraints

Repository names must be unique **per owner**.

```text
UNIQUE (owner_id, name)
```

Examples:

✅ Allowed

```
Alice
└── ecommerce

Bob
└── ecommerce
```

❌ Not Allowed

```
Alice
├── ecommerce
└── ecommerce
```

---

# Check Constraints

## Visibility

Allowed values:

- PRIVATE
- PUBLIC (Future)

Version 1 will support only **PRIVATE** repositories, but the column is designed to support future expansion without schema changes.

---

# Indexes

| Index | Purpose |
|--------|---------|
| idx_repositories_owner | Fetch repositories owned by a user |
| idx_repositories_visibility | Filter repositories by visibility |
| idx_repositories_name | Search repositories by name |

---

# Business Rules

- Every repository has exactly one owner.
- A repository cannot exist without an owner.
- Repository names are unique per owner.
- Repository ownership cannot be null.
- Deleting a repository removes all dependent data according to the deletion policy.

---

# Future Enhancements

The following fields are intentionally excluded from Version 1:

- archived
- star_count
- fork_count
- last_reviewed_at
- github_repository_id
- language
- tags
- repository_size
- storage_usage

These can be introduced in later schema migrations without affecting the current design.

---

# Example Record

| id | owner_id | name | visibility |
|----|----------|------|------------|
| a8d7... | 550e... | reviewforge | PRIVATE |

---

# Design Decisions

## Why store `owner_id`?

Every repository must have a single accountable owner responsible for administrative operations.

---

## Why `visibility` now?

Although Version 1 supports only private repositories, including the column now avoids a breaking schema migration when public repositories are introduced.

---

## Why `default_branch`?

Future Git integration, versioning, and pull request workflows will depend on a default branch. Including it now keeps the model future-ready while adding minimal complexity.

---

# Conclusion

The `repositories` table is the core of the ReviewForge data model. Nearly every feature in the platform—collaboration, file management, AI reviews, and documentation—depends on this table, making it one of the most critical components of the database design.