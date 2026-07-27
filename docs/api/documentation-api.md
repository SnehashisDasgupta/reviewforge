# Documentation API

## Purpose

This document defines the REST APIs responsible for AI-generated project documentation in ReviewForge.

The Documentation API enables users to generate and retrieve technical documentation for uploaded repositories, helping developers understand, maintain, and onboard to software projects more efficiently.

---

# Base URL

```text
/api/v1/documentation
```

---

# Resource Overview

Supported operations:

- Generate documentation
- Retrieve generated documentation
- List documentation history
- Export documentation (Future)

---

# Endpoints

## 1. Generate Documentation

### Endpoint

```http
POST /api/v1/documentation
```

### Purpose

Creates a documentation generation request.

### Authentication

JWT Required

### Allowed Roles

- OWNER
- ADMIN
- COLLABORATOR

### Request Body

| Field | Type | Required |
|---------|------|----------|
| repositoryId | UUID | ✅ |
| documentationType | Enum | ✅ |

Supported documentation types:

- README
- ARCHITECTURE
- API
- DATABASE
- CLASS
- COMPLETE_PROJECT

### Success Response

**202 Accepted**

```json
{
  "success": true,
  "message": "Documentation generation started.",
  "data": {
    "documentationId": "uuid",
    "status": "PENDING"
  }
}
```

---

## 2. Get Documentation

### Endpoint

```http
GET /api/v1/documentation/{documentationId}
```

### Purpose

Returns generated documentation.

The response may include:

- Markdown
- HTML
- Metadata

---

## 3. List Documentation

### Endpoint

```http
GET /api/v1/documentation
```

### Query Parameters

| Parameter | Description |
|------------|-------------|
| repositoryId | Filter by repository |
| type | Documentation type |
| page | Page number |
| size | Page size |

---

# Documentation Status Lifecycle

```text
PENDING
    │
    ▼
GENERATING
    │
 ┌──┴─────────┐
 │            │
 ▼            ▼
COMPLETED   FAILED
```

---

# Authorization

| Action | OWNER | ADMIN | COLLABORATOR | VIEWER |
|---------|:-----:|:-----:|:------------:|:------:|
| Generate Documentation | ✅ | ✅ | ✅ | ❌ |
| View Documentation | ✅ | ✅ | ✅ | ✅ |

---

# Future Enhancements

- PDF export
- HTML export
- Living documentation
- Version comparison
- Automatic regeneration after code changes
- Architecture diagrams
- Sequence diagrams

---

# Conclusion

The Documentation API provides AI-powered documentation generation that improves code understanding, onboarding, and long-term maintainability while supporting future intelligent documentation capabilities.