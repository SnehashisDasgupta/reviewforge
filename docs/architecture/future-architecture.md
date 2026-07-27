# Future Architecture

## Purpose

This document describes how ReviewForge will evolve from a production-ready modular monolith into a distributed AI-powered software engineering platform.

The Version 1 architecture intentionally remains simple while ensuring future scalability without major redesign.

---

# Version 1 Architecture

ReviewForge starts as a single Spring Boot application.

```text
React Frontend
        │
        ▼
Spring Boot Application
        │
 ┌──────┴──────┐
 │             │
Business Logic Database
        │
        ▼
 PostgreSQL
```

Characteristics:

- Modular monolith
- Single database
- JWT authentication
- Local file storage
- Synchronous communication

---

# Future Target Architecture

```text
                 React Frontend
                        │
                        ▼
                  API Gateway
                        │
    ┌───────────┬────────────┬────────────┐
    ▼           ▼            ▼            ▼
Authentication Repository   Review   Notification
    Service       Service    Service     Service
                        │
                        ▼
                 Python AI Service
                        │
                        ▼
                  Local/Remote LLM
                        │
                        ▼
              PostgreSQL + PGVector

Redis • Kafka • MinIO • Prometheus • Grafana
```

---

# Design Principles

- Start simple.
- Split services only when necessary.
- Keep service boundaries aligned with business domains.
- Prefer asynchronous communication for long-running tasks.
- Avoid distributed complexity until justified.

---

# Evolution Strategy

Each future phase should extend the architecture rather than replace it.

Major architectural changes should be incremental and backward compatible.

---

# Conclusion

The Version 1 architecture provides a stable foundation while allowing ReviewForge to grow into a scalable AI-powered engineering platform.