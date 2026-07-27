# Exception Handling

## Purpose

This document defines how exceptions are handled consistently across ReviewForge.

---

# Principles

- Use exceptions for exceptional situations only.
- Return meaningful HTTP status codes.
- Do not expose internal implementation details.
- Centralize exception handling.

---

# Common Exceptions

| Exception | HTTP Status |
|-----------|-------------|
| ValidationException | 400 |
| AuthenticationException | 401 |
| AccessDeniedException | 403 |
| ResourceNotFoundException | 404 |
| DuplicateResourceException | 409 |
| InternalServerException | 500 |

---

# Global Exception Handler

ReviewForge uses a centralized exception handler to convert exceptions into standard API responses.

Benefits:

- Consistent error responses
- Reduced duplicate code
- Easier maintenance

---

# Error Response

```json
{
  "success": false,
  "message": "Repository not found.",
  "errors": [],
  "timestamp": "2026-07-19T15:00:00Z"
}
```

---

# Conclusion

Centralized exception handling improves consistency, debugging, and the overall developer experience.