# Standard API Response Format

## Purpose

This document defines the standard response structure used by every REST API in ReviewForge.

A consistent response format simplifies frontend development, error handling, logging, testing, and API documentation.

All endpoints should return responses following these guidelines.

---

# Success Response

Every successful request returns the following structure:

```json
{
  "success": true,
  "message": "Repository created successfully.",
  "data": {},
  "timestamp": "2026-07-19T10:30:00Z"
}
```

## Fields

| Field | Description |
|---------|-------------|
| success | Indicates whether the request succeeded |
| message | Human-readable response message |
| data | Requested or created resource |
| timestamp | Time the response was generated |

---

# Error Response

Failed requests return:

```json
{
  "success": false,
  "message": "Validation failed.",
  "errors": [
    {
      "field": "email",
      "message": "Email is required."
    }
  ],
  "timestamp": "2026-07-19T10:35:00Z"
}
```

---

# Validation Error

Validation errors should identify the field causing the failure.

Example:

```json
{
  "success": false,
  "message": "Validation failed.",
  "errors": [
    {
      "field": "password",
      "message": "Password must contain at least 8 characters."
    }
  ]
}
```

---

# Pagination Response

Collection endpoints should include pagination metadata.

```json
{
  "success": true,
  "message": "Repositories retrieved successfully.",
  "data": {
    "content": [],
    "page": 0,
    "size": 20,
    "totalElements": 125,
    "totalPages": 7
  },
  "timestamp": "2026-07-19T10:40:00Z"
}
```

---

# HTTP Status Codes

| Status | Meaning |
|---------|---------|
| 200 OK | Request successful |
| 201 Created | Resource created |
| 204 No Content | Successful request with no response body |
| 400 Bad Request | Validation or request error |
| 401 Unauthorized | Authentication required |
| 403 Forbidden | Permission denied |
| 404 Not Found | Resource not found |
| 409 Conflict | Resource conflict |
| 500 Internal Server Error | Unexpected server error |

---

# Design Principles

- Responses must be predictable.
- Messages should be user-friendly.
- Internal implementation details must never be exposed.
- Error messages should help API consumers resolve issues.

---

# Conclusion

A standardized response format ensures consistency across all APIs and provides a better developer experience for frontend and third-party integrations.