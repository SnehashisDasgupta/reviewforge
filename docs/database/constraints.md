# Database Constraints

## Purpose

This document defines the database constraints used throughout ReviewForge.

Constraints ensure data integrity, prevent invalid relationships, and enforce important business rules at the database level.

---

# Primary Key Constraints

Every table uses a UUID primary key.

| Table | Primary Key |
|---------|------------|
| users | id |
| repositories | id |
| repository_members | id |
| repository_invitations | id |
| directories | id |
| code_files | id |
| file_versions | id |
| reviews | id |
| review_results | id |
| review_issues | id |
| documentations | id |
| documentation_versions | id |
| notifications | id |

---

# Foreign Key Constraints

## Repository Ownership

repositories.owner_id

→ users.id

---

## Repository Members

repository_members.repository_id

→ repositories.id

repository_members.user_id

→ users.id

---

## Repository Invitations

repository_invitations.repository_id

→ repositories.id

repository_invitations.invited_user_id

→ users.id

repository_invitations.invited_by_user_id

→ users.id

---

## Directories

directories.repository_id

→ repositories.id

directories.parent_directory_id

→ directories.id

---

## Code Files

code_files.repository_id

→ repositories.id

code_files.directory_id

→ directories.id

---

## File Versions

file_versions.code_file_id

→ code_files.id

---

## Reviews

reviews.repository_id

→ repositories.id

reviews.requested_by

→ users.id

---

## Review Results

review_results.review_id

→ reviews.id

---

## Review Issues

review_issues.review_result_id

→ review_results.id

review_issues.code_file_id

→ code_files.id

---

## Documentation

documentations.repository_id

→ repositories.id

documentations.generated_by

→ users.id

documentation_versions.documentation_id

→ documentations.id

---

## Notifications

notifications.user_id

→ users.id

---

# Unique Constraints

| Table | Constraint |
|---------|-----------|
| users | email |
| repositories | owner_id + name |
| repository_members | repository_id + user_id |
| directories | repository_id + parent_directory_id + name |
| code_files | directory_id + file_name |
| file_versions | code_file_id + version_number |
| documentation_versions | documentation_id + version_number |

---

# Design Philosophy

Business rules are enforced at both:

- Database level
- Application level

Critical integrity rules always remain protected by the database.

---

# Conclusion

A well-designed constraint strategy ensures that invalid data cannot enter the system, improving consistency and reducing application complexity.