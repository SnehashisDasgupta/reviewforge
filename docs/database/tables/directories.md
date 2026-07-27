# Directories Table Design

## Purpose

The `directories` table represents the hierarchical folder structure of a repository.

Instead of storing folder paths as plain strings, ReviewForge models directories as first-class entities. This allows efficient navigation, renaming, movement, and future visualization of the project structure.

---

# Responsibilities

The `directories` table is responsible for:

- Representing folders within a repository
- Maintaining parent-child relationships
- Organizing code files
- Supporting recursive traversal
- Enabling future folder-level features

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| repositories | Many Directories → One Repository |
| directories | One Directory → Many Child Directories (Self-reference) |
| code_files | One Directory → Many Code Files |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| repository_id | UUID | ❌ | Repository containing the directory |
| parent_directory_id | UUID | ✅ | Parent directory (NULL for root) |
| name | VARCHAR(255) | ❌ | Directory name |
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
repository_id REFERENCES repositories(id)

parent_directory_id REFERENCES directories(id)
```

---

# Unique Constraints

Directory names must be unique within the same parent directory.

```text
UNIQUE (repository_id, parent_directory_id, name)
```

This allows:

```text
src/main
src/test
```

while preventing duplicate sibling directories with the same name.

---

# Business Rules

- Every directory belongs to exactly one repository.
- Root directories have a NULL parent.
- Circular parent-child relationships are not allowed.
- A directory cannot belong to multiple repositories.

---

# Indexes

| Index | Purpose |
|--------|---------|
| idx_directories_repository | Fetch repository tree |
| idx_directories_parent | Fetch child directories |
| idx_directories_name | Search by directory name |

---

# Design Decisions

## Why use a self-referencing relationship?

A self-reference models a real folder hierarchy and allows unlimited nesting without changing the schema.

---

## Why not store the full path?

Paths change whenever folders are renamed or moved. Storing parent-child relationships keeps the structure normalized and avoids updating every descendant record after a rename.

---

# Future Enhancements

Potential additions include:

- directory_size
- last_modified_by
- folder_color
- custom_metadata
- folder_permissions

---

# Example Structure

```text
Repository
│
├── src
│     ├── main
│     │      ├── java
│     │      └── resources
│     └── test
│
└── docs
```

Each folder is represented by one row in the `directories` table.

---

# Conclusion

The `directories` table provides a scalable and normalized representation of a repository's folder hierarchy, enabling efficient navigation, future visualization, and advanced repository management features.