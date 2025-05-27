CREATE TABLE user_assigned_roles
(
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    role_id UUID NOT NULL REFERENCES user_roles (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);