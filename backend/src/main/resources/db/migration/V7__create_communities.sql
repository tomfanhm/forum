CREATE TABLE communities
(
    id            uuid PRIMARY KEY        DEFAULT gen_random_uuid(),
    name          text           NOT NULL UNIQUE,
    description   text,
    banner_url    text,
    icon_url      text,
    type          community_type NOT NULL DEFAULT 'PUBLIC',
    is_mature     boolean        NOT NULL DEFAULT FALSE,
    created_by    uuid           REFERENCES users (id) ON DELETE SET NULL,
    members_count integer        NOT NULL DEFAULT 0 CHECK (members_count >= 0),
    created_at    timestamptz    NOT NULL DEFAULT NOW(),
    updated_at    timestamptz    NOT NULL DEFAULT NOW()
);

CREATE TRIGGER update_communities_timestamp
    BEFORE UPDATE
    ON communities
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

