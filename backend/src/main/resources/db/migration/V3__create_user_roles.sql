CREATE TABLE user_roles
(
    id         uuid PRIMARY KEY     DEFAULT gen_random_uuid(),
    name       text        NOT NULL UNIQUE,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW()
);

CREATE TRIGGER update_user_roles_timestamp
    BEFORE UPDATE
    ON user_roles
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

INSERT INTO user_roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_MODERATOR'),
       ('ROLE_USER'),
       ('ROLE_PREMIUM_USER');

