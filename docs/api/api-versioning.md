# API Versioning Strategy

## Purpose

This document defines how API versions are managed in ReviewForge.

Versioning allows the API to evolve over time without breaking existing clients.

---

# Versioning Approach

ReviewForge uses **URI Versioning**.

All endpoints include the API version in the URL.

Example:

```text
/api/v1/auth/login
/api/v1/repositories
/api/v1/reviews
```

---

# Why URI Versioning?

We selected URI versioning because it is:

- Easy to understand
- Easy to document
- Supported by API gateways and proxies
- Commonly used in production REST APIs
- Simple for frontend applications to consume

---

# Versioning Rules

- Breaking changes require a new API version.
- Non-breaking improvements remain within the current version.
- Deprecated versions should remain available for a defined period before removal.
- New endpoints should be added without affecting existing APIs whenever possible.

---

# Future Example

Version 1:

```text
/api/v1/repositories
```

Version 2:

```text
/api/v2/repositories
```

Both versions may coexist while clients migrate.

---

# Backward Compatibility

Whenever possible:

- Preserve existing request structures.
- Preserve existing response structures.
- Avoid removing fields without introducing a new version.
- Introduce new optional fields instead of changing existing ones.

---

# Documentation

Each API version should have its own OpenAPI documentation.

Example:

```text
/swagger-ui/index.html
/v3/api-docs
```

Future versions should expose separate API specifications if they differ significantly.

---

# Conclusion

URI versioning provides a clear and maintainable strategy for evolving the ReviewForge API while protecting existing clients from breaking changes.