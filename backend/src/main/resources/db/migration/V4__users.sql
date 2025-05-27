CREATE TABLE users
(
    id                uuid PRIMARY KEY     DEFAULT gen_random_uuid(),
    firebase_uid      text UNIQUE NOT NULL,
    display_name      text,
    avatar_url        text,
    bio               text,
    location          text,
    website           text,
    email             text,
    email_verified    boolean     NOT NULL DEFAULT FALSE,
    is_active         boolean     NOT NULL DEFAULT TRUE,
    reputation_points integer     NOT NULL DEFAULT 0,
    created_at        timestamptz NOT NULL DEFAULT NOW(),
    updated_at        timestamptz NOT NULL DEFAULT NOW()
);

CREATE TRIGGER update_users_timestamp
    BEFORE UPDATE
    ON users
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();
