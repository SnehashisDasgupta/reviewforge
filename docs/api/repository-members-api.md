# Repository Members API

## Purpose

This document defines the REST APIs for managing repository members in ReviewForge.

Repository members enable collaboration by allowing multiple users to work on the same repository with different permission levels.

The APIs in this document cover inviting users, viewing members, updating roles, and removing members.

---

# Base URL

```text
/api/v1/repositories/{repositoryId}/members
```

---

# Resource Overview

Supported operations:

- List repository members
- Invite a member
- Update a member's role
- Remove a member

---

# Endpoints

## 1. List Members

### Endpoint

```http
GET /api/v1/repositories/{repositoryId}/members
```

### Purpose

Returns all members of the specified repository.

### Authentication

JWT Required

### Allowed Roles

- OWNER
- ADMIN
- COLLABORATOR
- VIEWER

### Success Response

**200 OK**

Returns a list containing:

- User ID
- Full Name
- Email
- Repository Role
- Joined At

### Possible Errors

| Status | Reason |
|---------|--------|
| 401 | Authentication required |
| 403 | Access denied |
| 404 | Repository not found |

---

## 2. Invite Member

### Endpoint

```http
POST /api/v1/repositories/{repositoryId}/members
```

### Purpose

Invites an existing ReviewForge user to join the repository.

### Authentication

JWT Required

### Allowed Roles

- OWNER
- ADMIN

### Request Body

| Field | Type | Required |
|---------|------|----------|
| email | String | ✅ |
| role | Enum | ✅ |

Allowed roles:

- ADMIN
- COLLABORATOR
- VIEWER

> The OWNER role cannot be assigned through this endpoint.

### Success Response

**201 Created**

```json
{
  "success": true,
  "message": "Member added successfully."
}
```

### Possible Errors

| Status | Reason |
|---------|--------|
| 400 | Validation failed |
| 403 | Permission denied |
| 404 | User or repository not found |
| 409 | User is already a member |

---

## 3. Update Member Role

### Endpoint

```http
PATCH /api/v1/repositories/{repositoryId}/members/{memberId}
```

### Purpose

Updates a repository member's role.

### Authentication

JWT Required

### Allowed Roles

- OWNER
- ADMIN*

> **ADMIN** cannot modify the OWNER's role or promote another user to OWNER.

### Request Body

| Field | Type | Required |
|---------|------|----------|
| role | Enum | ✅ |

### Success Response

**200 OK**

Returns the updated member information.

### Possible Errors

| Status | Reason |
|---------|--------|
| 403 | Permission denied |
| 404 | Member not found |
| 400 | Invalid role |

---

## 4. Remove Member

### Endpoint

```http
DELETE /api/v1/repositories/{repositoryId}/members/{memberId}
```

### Purpose

Removes a member from the repository.

### Authentication

JWT Required

### Allowed Roles

- OWNER
- ADMIN*

> **ADMIN** cannot remove the OWNER.

### Success Response

**204 No Content**

### Possible Errors

| Status | Reason |
|---------|--------|
| 403 | Permission denied |
| 404 | Member not found |

---

# Role Assignment Rules

| Current Role | Allowed to Assign |
|--------------|-------------------|
| OWNER | ADMIN, COLLABORATOR, VIEWER |
| ADMIN | COLLABORATOR, VIEWER |
| COLLABORATOR | None |
| VIEWER | None |

---

# Authorization Rules

- Every repository has exactly one OWNER.
- The OWNER role cannot be assigned through the member API.
- Ownership can only change through a dedicated ownership transfer process.
- A user cannot be added to the same repository more than once.
- Repository membership is required before accessing protected repository resources.

---

# Validation Rules

## Email

- Must belong to an existing ReviewForge account.
- Must not already be a repository member.

## Role

Allowed values:

```text
ADMIN
COLLABORATOR
VIEWER
```

---

# HTTP Status Codes

| Status | Meaning |
|---------|---------|
| 200 | Member updated |
| 201 | Member added |
| 204 | Member removed |
| 400 | Validation failed |
| 401 | Authentication required |
| 403 | Permission denied |
| 404 | Repository or member not found |
| 409 | Member already exists |

---

# Future Enhancements

Future releases may include:

- Invitation emails
- Invitation expiration
- Pending invitations
- Team-based membership
- Bulk member import
- Member activity history

---

# Conclusion

The Repository Members API enables secure collaboration by allowing repository owners and administrators to manage repository membership while enforcing role-based access control.