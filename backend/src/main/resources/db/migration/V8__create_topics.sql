CREATE TABLE topics
(
    id         uuid PRIMARY KEY     DEFAULT gen_random_uuid(),
    name       text        NOT NULL UNIQUE,
    count      integer     NOT NULL DEFAULT 0 CHECK (count >= 0),
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW()
);

CREATE TRIGGER update_topics_timestamp
    BEFORE UPDATE
    ON topics
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

