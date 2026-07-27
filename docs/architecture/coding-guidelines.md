# Coding Guidelines

## Purpose

This document defines the coding practices followed throughout ReviewForge.

---

# General Principles

- Follow SOLID principles.
- Prefer composition over inheritance.
- Keep methods focused on a single responsibility.
- Write self-documenting code.
- Avoid premature optimization.

---

# Dependency Injection

- Use constructor injection.
- Avoid field injection.

---

# DTO Usage

- Never expose JPA entities directly.
- Use request and response DTOs.

---

# Logging

- Use structured logging.
- Log meaningful events.
- Never log passwords or sensitive data.

---

# Validation

- Validate all external input.
- Fail fast with clear error messages.

---

# Testing

- Write unit tests for business logic.
- Add integration tests for APIs.
- Keep tests isolated and repeatable.

---

# Code Reviews

Before merging:

- Code compiles successfully.
- Tests pass.
- Naming follows conventions.
- No unused code.
- Documentation updated if needed.

---

# Conclusion

Consistent coding practices improve maintainability, reduce defects, and make collaboration more effective.