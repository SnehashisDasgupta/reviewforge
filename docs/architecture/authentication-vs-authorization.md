# Authentication vs Authorization

## Purpose

Authentication and authorization are closely related but solve different problems.

Understanding this distinction is essential for designing secure backend systems.

---

# Authentication

Authentication answers the question:

> **Who are you?**

Its purpose is to verify the identity of a user.

Examples:

- Login using email and password
- JWT validation
- OAuth login
- Multi-factor authentication (future)

If authentication succeeds, the application knows the identity of the requester.

---

# Authorization

Authorization answers the question:

> **What are you allowed to do?**

After a user is authenticated, the system checks whether they have permission to perform the requested action.

Examples:

- Can this user delete a repository?
- Can this user upload files?
- Can this user invite collaborators?

---

# Comparison

| Authentication | Authorization |
|----------------|---------------|
| Verifies identity | Verifies permissions |
| Happens first | Happens after authentication |
| Uses credentials | Uses roles and permissions |
| Returns 401 if invalid | Returns 403 if forbidden |

---

# Example Request

```text
User Login
     │
     ▼
Authentication
     │
     ▼
Authenticated User
     │
     ▼
Authorization
     │
     ▼
Allowed?
     │
 ┌───┴────┐
 │        │
 ▼        ▼
Yes      No
 │        │
 ▼        ▼
API     403 Forbidden
```

---

# Design Principles

- Authentication and authorization should remain separate concerns.
- Authentication establishes identity.
- Authorization evaluates permissions.
- Both are required for secure systems.

---

# Conclusion

Authentication proves **who the user is**, while authorization determines **what the user is allowed to do**. ReviewForge enforces both for every protected resource.