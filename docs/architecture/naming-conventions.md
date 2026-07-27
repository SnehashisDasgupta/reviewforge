# Naming Conventions

## Purpose

This document defines the naming standards used throughout ReviewForge to ensure consistency, readability, and maintainability.

---

# Java Classes

| Component | Convention | Example |
|-----------|------------|---------|
| Entity | PascalCase | Repository |
| DTO | PascalCase + Request/Response | CreateRepositoryRequest |
| Service | PascalCase + Service | RepositoryService |
| Controller | PascalCase + Controller | RepositoryController |
| Repository | PascalCase + Repository | RepositoryRepository |
| Mapper | PascalCase + Mapper | RepositoryMapper |
| Exception | PascalCase + Exception | ResourceNotFoundException |
| Enum | PascalCase | RepositoryVisibility |

---

# Methods

Use camelCase and meaningful verbs.

Examples:

- createRepository()
- updateRepository()
- findRepositoryById()
- generateDocumentation()

---

# Variables

Use camelCase with descriptive names.

Good:

```java
repositoryName
createdAt
reviewStatus
```

Avoid:

```java
r
temp
obj
```

---

# Database

| Component | Convention |
|-----------|------------|
| Table | snake_case plural |
| Column | snake_case |
| Foreign Key | `<table>_id` |
| Join Table | alphabetical order |

Examples:

```text
users
repositories
repository_members
created_at
owner_id
```

---

# API Endpoints

- Lowercase
- Plural nouns
- Kebab-case only if required
- No verbs in resource names

Examples:

```text
/api/v1/repositories
/api/v1/reviews
/api/v1/documentation
```

---

# Conclusion

Consistent naming reduces ambiguity, improves readability, and makes the codebase easier to navigate.