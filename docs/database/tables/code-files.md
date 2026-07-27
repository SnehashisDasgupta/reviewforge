# Code Files Table Design

## Purpose

The `code_files` table stores metadata about every file uploaded to a repository.

It does **not** store the actual file content. Instead, the file content is stored in the configured storage provider (local file system in Version 1), while this table maintains metadata required for organization, retrieval, and future versioning.

---

# Responsibilities

The `code_files` table is responsible for:

- Representing files within a repository
- Linking files to directories
- Tracking file metadata
- Providing storage location references
- Supporting file versioning
- Supporting future AI indexing

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| repositories | Many Files → One Repository |
| directories | Many Files → One Directory |
| file_versions | One File → Many Versions |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| repository_id | UUID | ❌ | Repository containing the file |
| directory_id | UUID | ❌ | Parent directory |
| file_name | VARCHAR(255) | ❌ | Name of the file |
| file_extension | VARCHAR(20) | ✅ | File extension (e.g. java, md, xml) |
| mime_type | VARCHAR(100) | ❌ | MIME type of the file |
| storage_path | TEXT | ❌ | Physical storage location |
| file_size | BIGINT | ❌ | File size in bytes |
| checksum | VARCHAR(64) | ❌ | SHA-256 hash of the file |
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

directory_id REFERENCES directories(id)
```

---

# Unique Constraints

A directory cannot contain two files with the same name.

```text
UNIQUE (directory_id, file_name)
```

---

# Business Rules

- Every file belongs to exactly one repository.
- Every file belongs to exactly one directory.
- The database stores metadata only.
- File contents are stored externally.
- File names are unique within a directory.
- The checksum represents the current file version.

---

# Indexes

| Index | Purpose |
|--------|---------|
| idx_code_files_repository | Fetch all files in a repository |
| idx_code_files_directory | Fetch files within a directory |
| idx_code_files_checksum | Duplicate detection and integrity verification |

---

# Design Decisions

## Why store `storage_path`?

The database references the physical location of the file, allowing the storage implementation to change (local file system, MinIO, Amazon S3) without changing the database schema.

---

## Why store `checksum`?

The checksum allows:

- File integrity verification
- Duplicate detection
- Efficient change detection
- Future incremental AI processing

---

# Future Enhancements

Future releases may add:

- file_encoding
- last_accessed_at
- storage_provider
- compression_type
- encryption_status

---

# Example Record

| id | file_name | storage_path | file_size |
|----|-----------|--------------|----------:|
| 0d2f... | UserService.java | storage/repository-1/src/main/java/UserService.java | 12,384 |

---

# Conclusion

The `code_files` table separates file metadata from file content, providing a scalable foundation for storage management, AI processing, and future cloud storage integration.