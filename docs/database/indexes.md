# Database Index Strategy

## Purpose

Indexes improve query performance by reducing the amount of data scanned during database operations.

ReviewForge indexes are designed based on expected application queries rather than indexing every column.

---

# Indexing Principles

- Index frequently searched columns.
- Index foreign keys.
- Avoid excessive indexes.
- Optimize read-heavy workloads.
- Review indexes as usage grows.

---

# User Indexes

- idx_users_email
- idx_users_status

---

# Repository Indexes

- idx_repositories_owner
- idx_repositories_visibility
- idx_repositories_name

---

# Collaboration Indexes

repository_members

- idx_repository_members_repository
- idx_repository_members_user
- idx_repository_members_role

repository_invitations

- idx_repository_invitations_repository
- idx_repository_invitations_invited_user
- idx_repository_invitations_status

---

# File System Indexes

directories

- idx_directories_repository
- idx_directories_parent

code_files

- idx_code_files_repository
- idx_code_files_directory
- idx_code_files_checksum

file_versions

- idx_file_versions_file

---

# AI Review Indexes

reviews

- idx_reviews_repository
- idx_reviews_requested_by
- idx_reviews_status

review_results

- idx_review_results_review

review_issues

- idx_review_issues_result
- idx_review_issues_file
- idx_review_issues_severity

---

# Documentation Indexes

documentations

- idx_documentations_repository
- idx_documentations_generated_by

documentation_versions

- idx_documentation_versions_documentation

---

# Notification Indexes

- idx_notifications_user
- idx_notifications_read
- idx_notifications_created_at

---

# Future Optimizations

- Full-text search
- PGVector indexes
- Partial indexes
- Composite indexes
- Materialized views

---

# Conclusion

Indexes should evolve with application usage and be driven by real query patterns rather than assumptions.