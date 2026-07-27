# Security Boundaries

## Purpose

This document defines where security responsibilities are enforced within the ReviewForge application.

Security is applied in layers so that no single layer is solely responsible for protecting the system.

---

# Security Layers

```text
Client
   │
   ▼
Spring Security Filter Chain
(Authentication)
   │
   ▼
Controller
(Input Validation)
   │
   ▼
Service Layer
(Authorization & Business Rules)
   │
   ▼
Repository Layer
(Database Access)
   │
   ▼
PostgreSQL
(Database Constraints)
```

---

# Layer Responsibilities

## Spring Security

Responsible for:

- JWT validation
- User authentication
- Establishing the security context

---

## Controllers

Responsible for:

- Request validation
- Input sanitization
- Returning appropriate HTTP responses

Controllers should **not** contain authorization logic.

---

## Service Layer

Responsible for:

- Repository ownership checks
- Role validation
- Permission evaluation
- Business rule enforcement

This is the primary location for authorization logic.

---

## Repository Layer

Responsible for:

- Database interactions only
- No business logic
- No authorization decisions

---

## Database

Responsible for:

- Primary keys
- Foreign keys
- Unique constraints
- Referential integrity

The database protects data integrity but does not replace application-level authorization.

---

# Security Principles

- Never trust client-side validation.
- Every protected request must be authenticated.
- Every protected operation must be authorized.
- Apply the Principle of Least Privilege.
- Fail securely by denying access when permission cannot be verified.

---

# Future Enhancements

Future versions may include:

- Method-level security
- Audit logging
- Rate limiting
- API key authentication
- Fine-grained permission policies
- Security monitoring

---

# Conclusion

ReviewForge applies security across multiple layers, ensuring that authentication, authorization, business rules, and database integrity work together to provide a robust and maintainable security architecture.