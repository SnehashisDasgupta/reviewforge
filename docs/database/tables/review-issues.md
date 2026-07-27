# Review Issues Table Design

## Purpose

The `review_issues` table stores individual findings detected during an AI review.

Each issue represents a specific problem, recommendation, or observation related to a file or section of code.

---

# Responsibilities

- Store issue details
- Categorize findings
- Track severity
- Link issues to files

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| review_results | Many Issues → One Review Result |
| code_files | Many Issues → One Code File |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| review_result_id | UUID | ❌ | Parent review result |
| code_file_id | UUID | ❌ | Associated file |
| title | VARCHAR(255) | ❌ | Issue title |
| description | TEXT | ❌ | Detailed explanation |
| severity | VARCHAR(20) | ❌ | Issue severity |
| category | VARCHAR(50) | ❌ | Issue category |
| line_number | INTEGER | ✅ | Affected line |
| suggestion | TEXT | ✅ | Suggested fix |
| created_at | TIMESTAMP WITH TIME ZONE | ❌ | Creation timestamp |

---

# Allowed Severity

- LOW
- MEDIUM
- HIGH
- CRITICAL

---

# Categories

- BUG
- SECURITY
- PERFORMANCE
- STYLE
- MAINTAINABILITY
- DOCUMENTATION

---

# Constraints

```text
PRIMARY KEY(id)

FOREIGN KEY(review_result_id)
REFERENCES review_results(id)

FOREIGN KEY(code_file_id)
REFERENCES code_files(id)
```

---

# Indexes

- idx_review_issues_result
- idx_review_issues_file
- idx_review_issues_severity

---

# Business Rules

- Every issue belongs to one review result.
- An issue may reference one file.
- Line number is optional for repository-level findings.

---

# Future Enhancements

- AI confidence score
- Autofix support
- GitHub PR comments
- Issue status
- User resolution tracking

---

# Conclusion

The `review_issues` table provides detailed, actionable feedback that developers can use to improve code quality.