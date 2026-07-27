# Documentation Table Design

## Purpose

The `documentations` table stores AI-generated documentation for a repository.

A documentation record represents a documentation generation request and serves as the parent entity for different documentation versions. This allows users to regenerate documentation whenever the repository changes while preserving previous versions.

---

# Responsibilities

The `documentations` table is responsible for:

- Tracking documentation generation requests
- Associating documentation with repositories
- Recording generation status
- Maintaining documentation history
- Supporting future export functionality

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| repositories | Many Documentations → One Repository |
| users | Many Documentations → One User |
| documentation_versions | One Documentation → Many Versions |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| repository_id | UUID | ❌ | Associated repository |
| generated_by | UUID | ❌ | User requesting generation |
| status | VARCHAR(30) | ❌ | Generation status |
| documentation_type | VARCHAR(50) | ❌ | Type of documentation |
| generated_at | TIMESTAMP WITH TIME ZONE | ✅ | Completion timestamp |
| created_at | TIMESTAMP WITH TIME ZONE | ❌ | Request creation |
| updated_at | TIMESTAMP WITH TIME ZONE | ❌ | Last update |

---

# Supported Documentation Types

- README
- ARCHITECTURE
- API
- DATABASE
- CLASS
- SEQUENCE_DIAGRAM
- DEPENDENCY_REPORT
- COMPLETE_PROJECT

---

# Allowed Status

- PENDING
- IN_PROGRESS
- COMPLETED
- FAILED

---

# Constraints

```text
PRIMARY KEY (id)

FOREIGN KEY (repository_id)
REFERENCES repositories(id)

FOREIGN KEY (generated_by)
REFERENCES users(id)
```

---

# Indexes

- idx_documentations_repository
- idx_documentations_generated_by
- idx_documentations_status

---

# Business Rules

- Every documentation belongs to one repository.
- A user may generate documentation multiple times.
- Completed documentation cannot be modified.
- Each generation creates a new documentation version.

---

# Future Enhancements

- Scheduled documentation
- Incremental generation
- AI model tracking
- Export history

---

# Conclusion

The `documentations` table acts as the entry point for AI-powered documentation generation and maintains the lifecycle of documentation requests.