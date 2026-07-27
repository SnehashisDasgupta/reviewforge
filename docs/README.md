# ReviewForge Documentation

Welcome to the official engineering documentation for **ReviewForge**.

This documentation captures the design decisions, architecture, API contracts, database models, and development roadmap for the project.

The goal is to document **not only what is built, but also why it is built that way**.

---

# About ReviewForge

ReviewForge is a production-grade AI-powered software engineering platform.

It helps developers:

- Manage software repositories
- Collaborate with team members
- Upload and organize source code
- Generate AI-assisted code reviews
- Generate technical documentation
- Understand large codebases
- Improve software quality

The project is being developed incrementally, starting as a modular monolith and evolving into a distributed AI platform.

---

# Documentation Structure

```text
docs/
├── README.md
├── roadmap.md
│
├── product/
│   ├── product-requirements.md
│   ├── domain-glossary.md
│   ├── user-stories.md
│   ├── functional-requirements.md
│   ├── non-functional-requirements.md
│   └── assumptions-and-release-scope.md
│
├── architecture/
│   ├── domain-model.md
│   ├── entity-relationship.md
│   ├── permission-model.md
│   ├── naming-conventions.md
│   ├── package-structure.md
│   ├── coding-guidelines.md
│   ├── exception-handling.md
│   ├── future-architecture.md
│   ├── migration-strategy.md
│   └── scalability-roadmap.md
│
├── database/
│   ├── database-schema.md
│   ├── tables.md
│   ├── constraints.md
│   ├── indexes.md
│   ├── naming-conventions.md
│   └── audit-and-versioning.md
│
├── api/
│   ├── api-design-principles.md
│   ├── api-response-format.md
│   ├── api-versioning.md
│   ├── authentication-api.md
│   ├── repository-api.md
│   ├── repository-members-api.md
│   ├── file-management-api.md
│   ├── review-api.md
│   └── documentation-api.md
│
└── adr/
    ├── ADR-001-modular-monolith.md
    ├── ADR-002-uuid-primary-keys.md
    ├── ADR-003-postgresql.md
    ├── ADR-004-flyway.md
    ├── ADR-005-jwt-authentication.md
    ├── ADR-006-feature-based-packaging.md
    ├── ADR-007-local-file-storage.md
    ├── ADR-008-local-ai-models.md
    ├── ADR-009-mapstruct.md
    └── ADR-010-hard-delete-v1.md
```

---

# Recommended Reading Order

If you're new to the project, read the documents in the following order:

## 1. Product

Start here to understand the business problem and product goals.

1. Product Requirements
2. Domain Glossary
3. User Stories
4. Functional Requirements
5. Non-Functional Requirements
6. Assumptions & Release Scope

---

## 2. Architecture

Learn how the system is modeled and structured.

1. Domain Model
2. Entity Relationship
3. Permission Model
4. Naming Conventions
5. Package Structure
6. Coding Guidelines
7. Exception Handling
8. Future Architecture
9. Migration Strategy
10. Scalability Roadmap

---

## 3. Database

Understand how data is stored and managed.

1. Database Schema
2. Tables
3. Constraints
4. Indexes
5. Naming Conventions
6. Audit & Versioning

---

## 4. API

Review the REST API contracts before implementation.

1. API Design Principles
2. Response Format
3. Versioning
4. Authentication API
5. Repository API
6. Repository Members API
7. File Management API
8. Review API
9. Documentation API

---

## 5. Architecture Decision Records (ADR)

Each ADR explains the reasoning behind important technical decisions.

Examples include:

- Why a modular monolith?
- Why PostgreSQL?
- Why UUID?
- Why Flyway?
- Why JWT?
- Why feature-based packaging?

---

# Development Workflow

Every feature in ReviewForge follows the same lifecycle:

```text
Business Requirement
        │
        ▼
Product Requirement
        │
        ▼
Domain Design
        │
        ▼
Database Design
        │
        ▼
API Design
        │
        ▼
Implementation
        │
        ▼
Testing
        │
        ▼
Documentation
        │
        ▼
Release
```

This ensures that design decisions are made before implementation, resulting in a maintainable and scalable codebase.

---

# Project Principles

The project follows these engineering principles:

- Business-first design
- Clean Architecture
- SOLID principles
- Feature-based packaging
- API-first development
- Database-first thinking
- Security by default
- Incremental evolution
- Production-ready code
- Documentation-driven development

---

# Project Status

| Phase | Status |
|--------|--------|
| Phase 0.1 – Project Foundation | ✅ Completed |
| Phase 0.2 – System Design | ✅ Completed |
| Phase 1 – Core Backend Development | 🚧 Next |

---

# Contributing

All contributors should:

- Follow the documented architecture.
- Adhere to coding guidelines and naming conventions.
- Update documentation when introducing new features or architectural decisions.
- Create a new ADR for significant technical decisions.

---

# License

This project is currently developed for educational, portfolio, and learning purposes. Licensing may be updated in future releases.