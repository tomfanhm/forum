CREATE TABLE community_moderators
(
    user_id      uuid        NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    community_id uuid        NOT NULL REFERENCES communities (id) ON DELETE CASCADE,
    assigned_at  timestamptz NOT NULL DEFAULT NOW(),
    PRIMARY KEY (user_id, community_id)
);

CREATE INDEX IF NOT EXISTS idx_community_moderators_community ON community_moderators (community_id);

