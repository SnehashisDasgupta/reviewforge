# Authorization Flow

## Purpose

This document describes how ReviewForge authorizes every protected request after a user has been authenticated.

The goal is to ensure that every request is validated consistently and securely before any business logic is executed.

---

# Overview

ReviewForge follows a layered authorization approach.

Every protected request passes through multiple validation steps before reaching the business logic.

```text
Client
   │
   ▼
JWT Authentication
   │
   ▼
Extract User Identity
   │
   ▼
Load Requested Resource
   │
   ▼
Determine User Repository Role
   │
   ▼
Check Required Permission
   │
   ▼
Permission Granted?
   │
 ┌─┴───────────────┐
 │                 │
 ▼                 ▼
Yes               No
 │                 │
 ▼                 ▼
Execute API     Return 403 Forbidden
```

---

# Authorization Steps

## Step 1 – Authenticate the User

The incoming JWT is validated.

If authentication fails:

- Return **401 Unauthorized**
- Stop request processing

---

## Step 2 – Identify the User

Extract the authenticated user's identity from the JWT.

This identity is used throughout the request lifecycle.

---

## Step 3 – Load the Target Resource

Load the requested repository or other protected resource.

If the resource does not exist:

- Return **404 Not Found**

---

## Step 4 – Determine User Role

Determine the user's repository role.

Possible roles:

- OWNER
- ADMIN
- COLLABORATOR
- VIEWER

If the user is not a member:

- Return **403 Forbidden**

---

## Step 5 – Validate Permission

Compare:

- User Role
- Requested Action

using the Permission Matrix.

Example:

```text
User Role

COLLABORATOR

Action

Delete Repository

Permission

Denied
```

---

## Step 6 – Execute Business Logic

If permission is granted, the request proceeds to the service layer for execution.

---

# Design Principles

- Authentication always precedes authorization.
- Authorization is evaluated for every protected request.
- Repository permissions are checked independently for each repository.
- UI restrictions never replace backend authorization.

---

# Future Enhancements

- Attribute-Based Access Control (ABAC)
- Team-based permissions
- Organization-level authorization
- Policy engine integration

---

# Conclusion

A consistent authorization flow ensures that every protected operation is evaluated using the same rules, improving both security and maintainability.