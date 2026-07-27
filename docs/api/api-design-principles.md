# API Design Principles

## Purpose

This document defines the REST API design standards followed throughout the ReviewForge backend.

The objective is to create APIs that are predictable, consistent, secure, and easy to consume by frontend applications, third-party clients, and future microservices.

All APIs implemented in ReviewForge must follow these principles.

---

# Design Philosophy

ReviewForge follows the REST architectural style.

Every API should:

- Represent a business resource.
- Use standard HTTP methods.
- Be stateless.
- Return meaningful HTTP status codes.
- Follow consistent naming conventions.
- Validate all incoming requests.
- Return a standardized response format.

---

# Resource-Oriented Design

APIs should represent business resources rather than database tables.

Good Examples:

```text
/api/v1/repositories
/api/v1/reviews
/api/v1/documentation
/api/v1/users
```

Avoid:

```text
/createRepository
/getRepositoryById
/deleteReview
```

The HTTP method should define the action, not the endpoint name.

---

# HTTP Methods

| Method | Purpose |
|----------|----------|
| GET | Retrieve resources |
| POST | Create resources |
| PUT | Replace a resource |
| PATCH | Partially update a resource |
| DELETE | Remove a resource |

Examples:

```text
GET    /api/v1/repositories
POST   /api/v1/repositories
GET    /api/v1/repositories/{repositoryId}
PATCH  /api/v1/repositories/{repositoryId}
DELETE /api/v1/repositories/{repositoryId}
```

---

# URI Naming Guidelines

Follow these conventions:

- Use nouns instead of verbs.
- Use lowercase letters.
- Use plural resource names.
- Separate path segments using `/`.
- Use path variables for resource identifiers.

Examples:

```text
/api/v1/repositories
/api/v1/repositories/{repositoryId}
/api/v1/repositories/{repositoryId}/members
/api/v1/repositories/{repositoryId}/files
```

---

# Request Validation

Every request must be validated before business logic is executed.

Validation includes:

- Required fields
- String length
- Email format
- UUID format
- Enum values
- Business constraints

Invalid requests return **400 Bad Request**.

---

# Pagination

Collection endpoints must support pagination.

Standard query parameters:

```text
?page=0
&size=20
&sort=createdAt,desc
```

This prevents large payloads and improves performance.

---

# Filtering & Searching

Filtering should use query parameters.

Examples:

```text
GET /api/v1/repositories?visibility=PUBLIC
GET /api/v1/reviews?status=COMPLETED
GET /api/v1/users?search=john
```

---

# Idempotency

- GET is idempotent.
- PUT is idempotent.
- DELETE is idempotent.
- POST is generally non-idempotent.

When appropriate, future versions may support idempotency keys for retry-safe operations.

---

# Security

Protected endpoints require JWT authentication.

Authorization is enforced based on the Permission Matrix.

Sensitive information must never be exposed in API responses.

---

# Documentation

Every public endpoint should be documented using OpenAPI (Swagger).

Documentation should include:

- Endpoint description
- Request body
- Response body
- Parameters
- HTTP status codes
- Authentication requirements

---

# Design Principles Summary

- Resource-oriented APIs
- Consistent naming
- Stateless communication
- Standard HTTP methods
- Proper status codes
- Input validation
- Pagination support
- Secure by default
- Well-documented

---

# Conclusion

These principles provide a consistent foundation for every REST API in ReviewForge, making the backend easier to develop, maintain, and integrate with clients.