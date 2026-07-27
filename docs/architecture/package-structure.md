# Package Structure

## Purpose

ReviewForge follows a feature-based package structure.

This groups all components related to a business feature together, improving modularity and maintainability.

---

# Root Package

```text
com.reviewforge
```

---

# Feature Structure

```text
repository/
├── controller
├── service
├── repository
├── dto
├── entity
├── mapper
├── validator
└── exception
```

---

# Shared Packages

```text
common/
config/
security/
exception/
util/
```

---

# Why Feature-Based Packaging?

- Better modularity
- Easier navigation
- Scales well as features grow
- Simplifies future microservice extraction

---

# Conclusion

Feature-based packaging aligns the project structure with business capabilities rather than technical layers.