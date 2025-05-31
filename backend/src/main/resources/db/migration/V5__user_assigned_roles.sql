CREATE TABLE user_assigned_roles
(
    user_id    uuid        NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    role_id    uuid        NOT NULL REFERENCES user_roles (id) ON DELETE CASCADE,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    PRIMARY KEY (user_id, role_id)
);

CREATE TRIGGER update_user_assigned_roles_timestamp
    BEFORE UPDATE
    ON user_assigned_roles
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

