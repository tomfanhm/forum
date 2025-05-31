CREATE TABLE user_subscriptions
(
    user_id      uuid        NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    community_id uuid        NOT NULL REFERENCES communities (id) ON DELETE CASCADE,
    created_at   timestamptz NOT NULL DEFAULT NOW(),
    PRIMARY KEY (user_id, community_id)
);

CREATE INDEX IF NOT EXISTS idx_user_subscriptions_community ON user_subscriptions (community_id);

