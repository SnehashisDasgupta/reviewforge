# ReviewForge - Database Design Principles

## Purpose

This document defines the database design standards and conventions used throughout ReviewForge.

Following consistent principles ensures the database remains maintainable, scalable, and easy to understand as the application grows.

All database objects, migrations, and future schema changes must follow these guidelines.

---

# Database Technology

| Item | Decision |
|------|----------|
| Database | PostgreSQL |
| Migration Tool | Flyway |
| ORM | Spring Data JPA (Hibernate) |
| Primary Key Strategy | UUID |
| Naming Convention | snake_case |
| Time Zone | UTC |
| Character Encoding | UTF-8 |

---

# Primary Key Strategy

## Decision

All primary keys will use **UUID**.

Example:

```text
550e8400-e29b-41d4-a716-446655440000
```

### Why UUID?

- Globally unique identifiers
- Safe for distributed systems
- Easier migration to microservices
- Avoids predictable sequential IDs
- Better support for data synchronization

### Trade-offs

**Advantages**

- Globally unique
- Better for distributed architectures
- Prevents ID enumeration attacks

**Disadvantages**

- Larger storage size than BIGINT
- Less human-readable
- Slightly larger indexes

For ReviewForge, the scalability and security benefits outweigh the storage cost.

---

# Naming Conventions

## Tables

- Use plural nouns.
- Use lowercase.
- Use snake_case.

Examples:

- users
- repositories
- repository_members
- review_results

---

## Columns

- Use lowercase.
- Use snake_case.
- Use descriptive names.

Examples:

- first_name
- created_at
- repository_id

Avoid abbreviations unless universally understood.

---

## Foreign Keys

Foreign key columns follow:

```text
<referenced_table_singular>_id
```

Examples:

- user_id
- repository_id
- review_id

---

## Constraints

Use meaningful names.

Examples:

```text
pk_users
fk_repository_owner
uk_users_email
ck_review_status
```

---

## Indexes

Follow this format:

```text
idx_table_column
```

Examples:

```text
idx_users_email
idx_reviews_status
idx_files_repository_id
```

---

# Audit Fields

Every business table should include:

| Column | Purpose |
|---------|----------|
| created_at | Creation timestamp |
| updated_at | Last modification timestamp |

Where applicable, future versions may also include:

- created_by
- updated_by

---

## Deletion Strategy

### Version 1

ReviewForge will use **hard deletion** for business entities.

When a resource is deleted, it is permanently removed from the database along with its dependent records according to the configured foreign key constraints.

This approach keeps the initial implementation simple and reduces query complexity during early development.

### Future Versions

As ReviewForge evolves into an enterprise-grade platform, business-critical entities will adopt a **soft delete** strategy.

The following audit fields will be introduced where applicable:

| Column | Purpose |
|---------|---------|
| deleted_at | Timestamp when the record was deleted |
| deleted_by | User who performed the deletion |

Soft deletion will enable:

- Resource recovery
- Audit trails
- Compliance requirements
- Historical reporting

The migration from hard delete to soft delete will be handled through future Flyway migrations without requiring significant changes to the overall schema design.

---

# Timestamp Strategy

All timestamps:

- Stored in UTC
- Managed by the backend
- Never rely on client-side timestamps

---

# Nullability Rules

Columns should only be nullable when the business domain explicitly allows missing values.

Default principle:

> If a value is mandatory in the business, it should be `NOT NULL` in the database.

---

# Relationship Principles

- Every foreign key must reference a valid parent.
- Orphan records should be avoided.
- Ownership relationships should be explicit.
- Cascading deletes should be used carefully.

---

# Enum Strategy

Business states such as:

- Review Status
- Member Role
- Invitation Status

will initially be stored as strings.

Advantages:

- Human-readable
- Easier migrations
- Safer than ordinal values

---

# Versioning Strategy

Historical entities (such as file versions and documentation versions) are immutable.

New versions create new rows rather than updating existing ones.

---

# Migration Strategy

Schema changes must always be applied using Flyway.

Rules:

- Never modify an executed migration.
- Create a new migration for every schema change.
- Keep migrations small and focused.
- Use descriptive migration names.

Example:

```text
V1__create_users_table.sql
V2__create_repositories_table.sql
V3__create_repository_members_table.sql
```

---

# Future Considerations

The schema is designed to support future enhancements without significant redesign.

Planned additions include:

- Multi-tenancy
- Organization workspaces
- Event sourcing
- Partitioning for large tables
- Read replicas
- PGVector integration
- Audit logging

---

# Conclusion

These principles provide a consistent foundation for all database design decisions in ReviewForge.

By defining standards before creating tables, the schema remains predictable, maintainable, and scalable as the platform evolves.