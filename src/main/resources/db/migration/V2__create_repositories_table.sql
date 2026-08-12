CREATE TABLE repositories(
    id UUID PRIMARY KEY ,
    name VARCHAR(150) NOT NULL ,
    description VARCHAR(500) ,
    visibility VARCHAR(20) NOT NULL ,
    owner_id UUID NOT NULL ,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL ,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL ,

    CONSTRAINT  fk_repositories_owner
                         FOREIGN KEY (owner_id)
                         REFERENCES users(id)
);