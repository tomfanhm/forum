CREATE TABLE community_topics
(
    community_id uuid NOT NULL REFERENCES communities (id) ON DELETE CASCADE,
    topic_id     uuid NOT NULL REFERENCES topics (id) ON DELETE CASCADE,
    PRIMARY KEY (community_id, topic_id)
);

CREATE INDEX IF NOT EXISTS idx_community_topics_topic ON community_topics(topic_id);

