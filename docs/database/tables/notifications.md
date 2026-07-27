# Notifications Table Design

## Purpose

The `notifications` table stores system-generated notifications delivered to users.

Notifications keep users informed about important events such as completed AI reviews, collaboration invitations, documentation generation, and future platform activities.

---

# Responsibilities

The `notifications` table is responsible for:

- Delivering user notifications
- Tracking read/unread status
- Recording notification history
- Supporting future real-time notifications

---

# Relationships

| Related Table | Relationship |
|---------------|--------------|
| users | Many Notifications → One User |

---

# Columns

| Column | Type | Nullable | Description |
|---------|------|----------|-------------|
| id | UUID | ❌ | Primary key |
| user_id | UUID | ❌ | Recipient |
| title | VARCHAR(255) | ❌ | Notification title |
| message | TEXT | ❌ | Notification content |
| type | VARCHAR(50) | ❌ | Notification type |
| is_read | BOOLEAN | ❌ | Read status |
| created_at | TIMESTAMP WITH TIME ZONE | ❌ | Creation timestamp |
| updated_at | TIMESTAMP WITH TIME ZONE | ❌ | Last update |

---

# Supported Notification Types

- REVIEW_COMPLETED
- REVIEW_FAILED
- DOCUMENTATION_READY
- INVITATION_RECEIVED
- MEMBER_ADDED
- SYSTEM

---

# Constraints

```text
PRIMARY KEY(id)

FOREIGN KEY(user_id)
REFERENCES users(id)
```

---

# Indexes

- idx_notifications_user
- idx_notifications_read
- idx_notifications_created_at

---

# Business Rules

- Every notification belongs to one user.
- Notifications are immutable except for the read status.
- Users can mark notifications as read or unread.
- Notifications are displayed in reverse chronological order.

---

# Future Enhancements

- Push notifications
- Email notifications
- WebSocket support
- Notification preferences
- Priority levels

---

# Conclusion

The `notifications` table provides a centralized mechanism for delivering important system events to users and lays the foundation for future real-time communication.