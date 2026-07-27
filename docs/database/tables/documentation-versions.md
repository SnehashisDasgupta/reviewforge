# Documentation Versions Table Design

## Purpose

The `documentation_versions` table stores every generated version of documentation.

Instead of replacing previously generated documentation, ReviewForge preserves historical versions so users can compare documentation over time.

---

# Responsibilities

- Maintain documentation history
- Support version comparison
- Enable rollback
- Store generated documentation metadata

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| documentations | Many Versions → One Documentation |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| documentation_id | UUID | ❌ | Parent documentation |
| version_number | INTEGER | ❌ | Documentation version |
| storage_path | TEXT | ❌ | Storage location |
| format | VARCHAR(20) | ❌ | Output format |
| file_size | BIGINT | ❌ | File size |
| checksum | VARCHAR(64) | ❌ | SHA-256 checksum |
| created_at | TIMESTAMP WITH TIME ZONE | ❌ | Generation timestamp |

---

# Supported Formats

- MARKDOWN
- PDF
- HTML

---

# Constraints

```text
PRIMARY KEY(id)

FOREIGN KEY(documentation_id)
REFERENCES documentations(id)

UNIQUE(documentation_id, version_number)
```

---

# Indexes

- idx_documentation_versions_documentation

---

# Business Rules

- Version numbers are sequential.
- Historical versions are immutable.
- Generated files are stored externally.
- Metadata is stored in the database.

---

# Future Enhancements

- DOCX export
- Diff comparison
- AI change summary
- Automatic cleanup policy

---

# Conclusion

The `documentation_versions` table preserves the evolution of generated documentation and provides the basis for version history.