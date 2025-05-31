CREATE
    OR REPLACE FUNCTION update_topic_count()
    RETURNS TRIGGER AS
$$
BEGIN
    IF
        TG_OP = 'INSERT' THEN
        UPDATE topics
        SET count = count + 1
        WHERE id = NEW.topic_id;
        RETURN NEW;
    END IF;
    IF
        TG_OP = 'DELETE' THEN
        UPDATE topics
        SET count = count - 1
        WHERE id = OLD.topic_id;
        RETURN OLD;
    END IF;
    IF
        TG_OP = 'UPDATE' THEN
        IF OLD.topic_id != NEW.topic_id THEN
            UPDATE topics
            SET count = count - 1
            WHERE id = OLD.topic_id;

            UPDATE topics
            SET count = count + 1
            WHERE id = NEW.topic_id;
        END IF;
        RETURN NEW;
    END IF;

    RETURN NULL;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_topic_count
    AFTER INSERT OR
        UPDATE OR
        DELETE
    ON community_topics
    FOR EACH ROW
EXECUTE FUNCTION update_topic_count();