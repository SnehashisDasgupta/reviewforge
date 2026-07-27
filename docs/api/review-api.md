# Review API

## Purpose

This document defines the REST APIs responsible for AI-assisted code reviews in ReviewForge.

The Review API allows users to submit repositories or specific files for analysis. The AI service evaluates the code and provides actionable feedback on code quality, maintainability, performance, security, and best practices.

---

# Base URL

```text
/api/v1/reviews
```

---

# Resource Overview

Supported operations:

- Create a review request
- View review details
- List review history
- Cancel a review (Future)

---

# Endpoints

## 1. Request AI Review

### Endpoint

```http
POST /api/v1/reviews
```

### Purpose

Creates a new AI review request for a repository or selected files.

### Authentication

JWT Required

### Allowed Roles

- OWNER
- ADMIN
- COLLABORATOR

### Request Body

| Field | Type | Required | Description |
|---------|------|----------|-------------|
| repositoryId | UUID | ✅ | Repository to review |
| reviewScope | Enum | ✅ | FULL_REPOSITORY or SELECTED_FILES |
| filePaths | List<String> | ❌ | Required when reviewing selected files |

### Success Response

**202 Accepted**

```json
{
  "success": true,
  "message": "Review request submitted successfully.",
  "data": {
    "reviewId": "uuid",
    "status": "PENDING"
  }
}
```

> **202 Accepted** is used because AI review is an asynchronous process.

---

## 2. Get Review Details

### Endpoint

```http
GET /api/v1/reviews/{reviewId}
```

### Purpose

Returns the complete review result.

### Response

- Review status
- Summary
- Findings
- Suggestions
- Metrics
- Completion time

---

## 3. List Reviews

### Endpoint

```http
GET /api/v1/reviews
```

### Query Parameters

| Parameter | Description |
|------------|-------------|
| repositoryId | Filter by repository |
| status | PENDING, RUNNING, COMPLETED, FAILED |
| page | Page number |
| size | Page size |

---

# Review Status Lifecycle

```text
PENDING
    │
    ▼
RUNNING
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
| Request Review | ✅ | ✅ | ✅ | ❌ |
| View Review | ✅ | ✅ | ✅ | ✅ |

---

# Future Enhancements

- Incremental reviews
- Pull request reviews
- Scheduled reviews
- Review comparison
- Review history timeline
- Streaming AI responses

---

# Conclusion

The Review API provides asynchronous AI-powered code analysis while supporting future expansion into advanced software engineering workflows.