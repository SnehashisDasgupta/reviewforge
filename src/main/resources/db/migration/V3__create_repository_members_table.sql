CREATE TABLE repository_members (
    id UUID PRIMARY KEY ,
    repository_id UUID NOT NULL ,
    user_id UUID NOT NULL ,
    role VARCHAR(20) NOT NULL ,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL ,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL ,

    CONSTRAINT fk_repository_members_repository
                                FOREIGN KEY  (repository_id)
                                REFERENCES repositories(id),

    CONSTRAINT fk_repository_members_user
                                FOREIGN KEY (user_id)
                                REFERENCES users(id),

    CONSTRAINT uk_repository_member
                                UNIQUE (repository_id, user_id)
);