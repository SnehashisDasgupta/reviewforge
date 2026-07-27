# Reviews Table Design

## Purpose

The `reviews` table represents an AI review request initiated by a user for a specific repository.

A review acts as the parent entity for the AI review process. It records who initiated the review, which repository is being reviewed, the review type, current status, and execution timestamps.

Each review produces one or more review results and issues.

---

# Responsibilities

The `reviews` table is responsible for:

- Tracking AI review requests
- Recording review lifecycle
- Associating reviews with repositories
- Tracking execution status
- Supporting future review history

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| repositories | Many Reviews → One Repository |
| users | Many Reviews → One User |
| review_results | One Review → Many Review Results |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| repository_id | UUID | ❌ | Repository under review |
| requested_by | UUID | ❌ | User requesting the review |
| review_type | VARCHAR(30) | ❌ | Type of review |
| status | VARCHAR(30) | ❌ | Review status |
| started_at | TIMESTAMP WITH TIME ZONE | ✅ | Processing start time |
| completed_at | TIMESTAMP WITH TIME ZONE | ✅ | Processing completion time |
| created_at | TIMESTAMP WITH TIME ZONE | ❌ | Request creation |
| updated_at | TIMESTAMP WITH TIME ZONE | ❌ | Last modification |

---

# Allowed Review Types

- FULL_REPOSITORY
- DIRECTORY
- FILE

---

# Allowed Status

- PENDING
- IN_PROGRESS
- COMPLETED
- FAILED
- CANCELLED

---

# Constraints

```text
PRIMARY KEY (id)

FOREIGN KEY (repository_id)
REFERENCES repositories(id)

FOREIGN KEY (requested_by)
REFERENCES users(id)
```

---

# Indexes

- idx_reviews_repository
- idx_reviews_requested_by
- idx_reviews_status

---

# Business Rules

- A review always belongs to one repository.
- Only authorized users may request reviews.
- Completed reviews cannot be modified.
- Every review must produce at least one review result.

---

# Future Enhancements

- AI model used
- Processing duration
- Cost estimation
- Review priority
- Scheduled reviews

---

# Conclusion

The `reviews` table is the entry point for every AI-powered analysis performed within ReviewForge.