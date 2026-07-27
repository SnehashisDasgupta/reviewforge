# Repository API

## Purpose

This document defines the REST APIs responsible for managing repositories in ReviewForge.

A repository is the central resource of the platform. It acts as a container for source code, members, AI reviews, documentation, and future software engineering features.

All repository-related operations are defined in this document.

---

# Base URL

```text
/api/v1/repositories
```

---

# Resource Overview

A repository supports the following operations:

- Create
- Retrieve
- List
- Update
- Delete
- Search
- Archive (Future)
- Transfer Ownership (Future)

---

# Endpoints

## 1. Create Repository

### Endpoint

```http
POST /api/v1/repositories
```

### Purpose

Creates a new repository owned by the authenticated user.

### Authentication

JWT Required

### Request Body

| Field | Type | Required | Validation |
|---------|------|----------|------------|
| name | String | ✅ | 3–100 characters, unique per owner |
| description | String | ❌ | Maximum 500 characters |
| visibility | Enum | ✅ | PUBLIC or PRIVATE |

### Success Response

**201 Created**

```json
{
  "success": true,
  "message": "Repository created successfully.",
  "data": {
    "id": "uuid",
    "name": "ReviewForge"
  }
}
```

### Possible Errors

| Status | Reason |
|---------|--------|
| 400 | Validation failed |
| 401 | Authentication required |
| 409 | Repository name already exists for the owner |

---

## 2. Get Repository

### Endpoint

```http
GET /api/v1/repositories/{repositoryId}
```

### Purpose

Returns repository details.

### Authentication

JWT Required

### Success Response

**200 OK**

Returns repository metadata including:

- ID
- Name
- Description
- Visibility
- Owner
- Created Date
- Updated Date

### Possible Errors

| Status | Reason |
|---------|--------|
| 401 | Authentication required |
| 403 | Access denied |
| 404 | Repository not found |

---

## 3. List Repositories

### Endpoint

```http
GET /api/v1/repositories
```

### Purpose

Returns repositories accessible to the authenticated user.

### Authentication

JWT Required

### Query Parameters

| Parameter | Description |
|------------|-------------|
| page | Page number |
| size | Page size |
| sort | Sorting field |
| visibility | Filter by visibility |
| search | Search by repository name |

### Success Response

**200 OK**

Returns a paginated list of repositories.

---

## 4. Update Repository

### Endpoint

```http
PATCH /api/v1/repositories/{repositoryId}
```

### Purpose

Updates repository information.

### Authentication

JWT Required

### Allowed Roles

- OWNER
- ADMIN

### Updatable Fields

- Name
- Description
- Visibility

### Possible Errors

| Status | Reason |
|---------|--------|
| 400 | Validation failed |
| 403 | Permission denied |
| 404 | Repository not found |

---

## 5. Delete Repository

### Endpoint

```http
DELETE /api/v1/repositories/{repositoryId}
```

### Purpose

Permanently deletes a repository.

### Authentication

JWT Required

### Allowed Roles

- OWNER only

### Success Response

**204 No Content**

### Possible Errors

| Status | Reason |
|---------|--------|
| 403 | Permission denied |
| 404 | Repository not found |

---

## 6. Search Repositories

### Endpoint

```http
GET /api/v1/repositories/search
```

### Purpose

Searches repositories by name.

### Authentication

JWT Required

### Query Parameters

| Parameter | Description |
|------------|-------------|
| keyword | Repository name |
| page | Page number |
| size | Page size |

---

# Repository Visibility

Supported values:

```text
PUBLIC
PRIVATE
```

Version 1 does not support internal or organization-level visibility.

---

# Validation Rules

## Repository Name

- Required
- 3–100 characters
- Unique for each owner
- Trim leading/trailing spaces

## Description

- Optional
- Maximum 500 characters

---

# Authorization

Repository permissions follow the Permission Matrix.

Examples:

| Action | Required Role |
|---------|---------------|
| Create Repository | USER |
| Update Repository | OWNER / ADMIN |
| Delete Repository | OWNER |
| View Repository | Member or Public Access |

---

# HTTP Status Codes

| Status | Meaning |
|---------|---------|
| 200 | Request successful |
| 201 | Repository created |
| 204 | Repository deleted |
| 400 | Validation failed |
| 401 | Authentication required |
| 403 | Permission denied |
| 404 | Repository not found |
| 409 | Repository already exists |

---

# Future Endpoints

Future releases may introduce:

```http
POST   /api/v1/repositories/{repositoryId}/archive
POST   /api/v1/repositories/{repositoryId}/restore
POST   /api/v1/repositories/{repositoryId}/transfer-ownership
GET    /api/v1/repositories/{repositoryId}/statistics
GET    /api/v1/repositories/{repositoryId}/activity
```

---

# Conclusion

The Repository API provides the core resource management capabilities of ReviewForge and establishes the foundation for collaboration, file management, AI reviews, and documentation generation.