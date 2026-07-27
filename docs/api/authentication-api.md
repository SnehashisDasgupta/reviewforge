# Authentication API

## Purpose

This document defines the REST APIs responsible for user authentication in ReviewForge.

Authentication is the first feature implemented in Version 1 and serves as the entry point to all protected resources.

ReviewForge uses **JWT (JSON Web Token)** for stateless authentication.

---

# Authentication Flow

```text
User Registers
      │
      ▼
Account Created
      │
      ▼
User Logs In
      │
      ▼
JWT Generated
      │
      ▼
Client Stores JWT
      │
      ▼
JWT Sent with Every Protected Request
      │
      ▼
Spring Security Validates JWT
      │
      ▼
Request Authorized
```

---

# Base URL

```text
/api/v1/auth
```

---

# Endpoints

## 1. Register User

### Endpoint

```http
POST /api/v1/auth/register
```

### Purpose

Creates a new user account.

### Authentication

Not Required

### Request Body

| Field | Type | Required | Validation |
|---------|------|----------|------------|
| fullName | String | ✅ | 3–100 characters |
| email | String | ✅ | Valid email, unique |
| password | String | ✅ | Minimum 8 characters |

### Success Response

**201 Created**

```json
{
  "success": true,
  "message": "User registered successfully.",
  "data": {
    "userId": "uuid"
  }
}
```

### Possible Errors

| Status | Reason |
|---------|--------|
| 400 | Validation failed |
| 409 | Email already exists |

---

## 2. Login

### Endpoint

```http
POST /api/v1/auth/login
```

### Purpose

Authenticates a user and returns a JWT.

### Authentication

Not Required

### Request Body

| Field | Type | Required |
|---------|------|----------|
| email | String | ✅ |
| password | String | ✅ |

### Success Response

**200 OK**

```json
{
  "success": true,
  "message": "Login successful.",
  "data": {
    "accessToken": "jwt-token",
    "tokenType": "Bearer",
    "expiresIn": 3600
  }
}
```

### Possible Errors

| Status | Reason |
|---------|--------|
| 400 | Invalid request |
| 401 | Invalid credentials |

---

## 3. Get Current User

### Endpoint

```http
GET /api/v1/auth/me
```

### Purpose

Returns the profile of the authenticated user.

### Authentication

JWT Required

### Success Response

**200 OK**

```json
{
  "success": true,
  "message": "User retrieved successfully.",
  "data": {
    "id": "uuid",
    "fullName": "John Doe",
    "email": "john@example.com"
  }
}
```

### Possible Errors

| Status | Reason |
|---------|--------|
| 401 | Missing or invalid JWT |

---

## 4. Refresh Access Token *(Future)*

### Endpoint

```http
POST /api/v1/auth/refresh
```

### Purpose

Generates a new access token using a valid refresh token.

### Status

Planned for a future release.

---

## 5. Logout *(Future)*

### Endpoint

```http
POST /api/v1/auth/logout
```

### Purpose

Invalidates the current session or refresh token.

### Status

Planned for a future release.

---

# Authentication Header

Protected endpoints require the following header:

```http
Authorization: Bearer <JWT>
```

---

# Validation Rules

### Email

- Required
- Valid email format
- Unique during registration

### Password

- Minimum 8 characters
- Maximum 128 characters

Future versions may require:

- Uppercase letters
- Lowercase letters
- Numbers
- Special characters

### Full Name

- Required
- 3–100 characters
- Leading and trailing spaces trimmed

---

# Security Considerations

- Passwords are never stored in plain text.
- Passwords are hashed using BCrypt.
- JWTs are digitally signed.
- Sensitive information is never returned in API responses.
- Authentication failures should not reveal whether the email or password was incorrect.

---

# HTTP Status Codes

| Status | Meaning |
|---------|---------|
| 200 | Login successful |
| 201 | User registered |
| 400 | Validation error |
| 401 | Authentication failed |
| 409 | Duplicate email |
| 500 | Internal server error |

---

# Future Enhancements

Future versions may include:

- Refresh tokens
- Multi-factor authentication (MFA)
- OAuth (Google/GitHub)
- Password reset
- Email verification
- Account lockout after repeated failed attempts
- Session management

---

# Conclusion

The Authentication API provides secure identity verification for ReviewForge users and serves as the foundation for all protected operations within the platform.