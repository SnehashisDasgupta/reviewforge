# File Management API

## Purpose

This document defines the REST APIs responsible for managing files and directories within a repository.

The File Management API enables users to upload, browse, update, download, and delete source code files while preserving the repository structure.

All file operations are performed within the context of a repository and are protected by repository-level authorization.

---

# Base URL

```text
/api/v1/repositories/{repositoryId}/files
```

---

# Resource Overview

Supported operations:

- Upload files
- Upload directories
- Browse repository files
- Retrieve file details
- Download files
- Update files
- Delete files
- Browse directory structure

---

# Endpoints

## 1. Upload Files

### Endpoint

```http
POST /api/v1/repositories/{repositoryId}/files
```

### Purpose

Uploads one or more source code files to the repository.

### Authentication

JWT Required

### Allowed Roles

- OWNER
- ADMIN
- COLLABORATOR

### Request

Multipart Form Data

| Field | Type | Required |
|---------|------|----------|
| files | File[] | ✅ |
| path | String | ❌ |

The optional `path` field specifies the destination directory inside the repository.

### Success Response

**201 Created**

Returns metadata for the uploaded files.

---

## 2. Browse Files

### Endpoint

```http
GET /api/v1/repositories/{repositoryId}/files
```

### Purpose

Returns the contents of a directory.

### Query Parameters

| Parameter | Description |
|------------|-------------|
| path | Directory path (defaults to repository root) |

### Allowed Roles

- OWNER
- ADMIN
- COLLABORATOR
- VIEWER

---

## 3. Get File Details

### Endpoint

```http
GET /api/v1/repositories/{repositoryId}/files/{fileId}
```

### Purpose

Returns metadata for a specific file.

Returned information includes:

- File name
- Relative path
- Size
- File type
- Last modified date

---

## 4. Download File

### Endpoint

```http
GET /api/v1/repositories/{repositoryId}/files/{fileId}/download
```

### Purpose

Downloads the original file.

### Allowed Roles

- OWNER
- ADMIN
- COLLABORATOR
- VIEWER

---

## 5. Update File

### Endpoint

```http
PUT /api/v1/repositories/{repositoryId}/files/{fileId}
```

### Purpose

Replaces the content of an existing file.

### Allowed Roles

- OWNER
- ADMIN
- COLLABORATOR

---

## 6. Delete File

### Endpoint

```http
DELETE /api/v1/repositories/{repositoryId}/files/{fileId}
```

### Purpose

Deletes a file from the repository.

### Allowed Roles

- OWNER
- ADMIN

---

# Validation Rules

- Files must belong to the specified repository.
- Duplicate file paths are not allowed.
- Empty files are rejected.
- Unsupported file types may be restricted in future versions.

---

# Authorization

| Action | OWNER | ADMIN | COLLABORATOR | VIEWER |
|---------|:-----:|:-----:|:------------:|:------:|
| Upload | ✅ | ✅ | ✅ | ❌ |
| Browse | ✅ | ✅ | ✅ | ✅ |
| Download | ✅ | ✅ | ✅ | ✅ |
| Update | ✅ | ✅ | ✅ | ❌ |
| Delete | ✅ | ✅ | ❌ | ❌ |

---

# HTTP Status Codes

| Status | Meaning |
|---------|---------|
| 200 | Request successful |
| 201 | File uploaded |
| 204 | File deleted |
| 400 | Validation failed |
| 401 | Authentication required |
| 403 | Permission denied |
| 404 | File or repository not found |
| 409 | Duplicate file path |

---

# Future Enhancements

Future releases may include:

- Folder upload
- File version history
- File restore
- Drag-and-drop upload
- Large file upload
- Chunked upload
- File locking
- Syntax highlighting
- In-browser code editor

---

# Conclusion

The File Management API provides secure and structured management of repository source code while serving as the foundation for AI reviews, documentation generation, and future versioning capabilities.