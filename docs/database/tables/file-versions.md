# File Versions Table Design

## Purpose

The `file_versions` table stores the historical versions of every file uploaded to ReviewForge.

Instead of overwriting files, every modification creates a new version. This preserves history, enables future comparison features, and provides a foundation for AI-powered change analysis.

Version 1 will support basic version tracking. Future versions will introduce Git synchronization and advanced diff analysis.

---

# Responsibilities

The `file_versions` table is responsible for:

- Maintaining file history
- Tracking version numbers
- Recording file metadata at each version
- Preserving immutable historical records
- Supporting future rollback and comparison features

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| code_files | Many File Versions → One Code File |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| code_file_id | UUID | ❌ | Associated file |
| version_number | INTEGER | ❌ | Sequential version number |
| storage_path | TEXT | ❌ | Storage location for this version |
| file_size | BIGINT | ❌ | Size in bytes |
| checksum | VARCHAR(64) | ❌ | SHA-256 checksum |
| change_summary | TEXT | ✅ | Optional description of changes |
| created_at | TIMESTAMP WITH TIME ZONE | ❌ | Version creation timestamp |

---

# Primary Key

```text
PRIMARY KEY (id)
```

---

# Foreign Keys

```text
code_file_id REFERENCES code_files(id)
```

---

# Unique Constraints

Each file can have only one record for a specific version number.

```text
UNIQUE (code_file_id, version_number)
```

---

# Business Rules

- Every version belongs to exactly one file.
- Version numbers are sequential.
- Historical versions are immutable.
- New uploads create a new version rather than updating an existing one.

---

# Indexes

| Index | Purpose |
|--------|---------|
| idx_file_versions_file | Fetch version history |
| idx_file_versions_version | Retrieve a specific version |

---

# Design Decisions

## Why store `storage_path` again?

Each version may point to a different physical file. Storing the path at the version level ensures historical versions remain accessible even after newer versions are uploaded.

---

# Future Enhancements

Future releases may add:

- uploaded_by
- diff_reference
- ai_summary
- branch_name
- commit_reference

---

# Example Record

| id | code_file_id | version_number | checksum |
|----|--------------|---------------:|----------|
| 4c2e... | 9a8d... | 3 | 5c3f... |

---

# Conclusion

The `file_versions` table preserves the complete evolution of a file and provides the foundation for version history, rollback, and future Git integration.