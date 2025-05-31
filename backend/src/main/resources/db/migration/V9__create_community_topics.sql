CREATE TABLE community_topics
(
    community_id uuid        NOT NULL REFERENCES communities (id) ON DELETE CASCADE,
    topic_id     uuid        NOT NULL REFERENCES topics (id) ON DELETE CASCADE,
    created_at   timestamptz NOT NULL DEFAULT NOW(),
    updated_at   timestamptz NOT NULL DEFAULT NOW(),
    PRIMARY KEY (community_id, topic_id)
);

CREATE TRIGGER update_community_topics_timestamp
    BEFORE UPDATE
    ON community_topics
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE INDEX IF NOT EXISTS idx_community_topics_topic ON community_topics(topic_id);

