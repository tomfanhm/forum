-- Subscribe
CREATE
OR REPLACE FUNCTION subscribe_to_community(p_user_id uuid, p_community_id uuid)
    RETURNS void
    AS $$
BEGIN
INSERT INTO user_subscriptions(user_id, community_id)
VALUES (p_user_id, p_community_id) ON CONFLICT
        DO NOTHING;
END;
$$
LANGUAGE plpgsql;

-- Unsubscribe
CREATE
OR REPLACE FUNCTION unsubscribe_from_community(p_user_id uuid, p_community_id uuid)
    RETURNS void
    AS $$
BEGIN
DELETE
FROM user_subscriptions
WHERE user_id = p_user_id
  AND community_id = p_community_id;
END;
$$
LANGUAGE plpgsql;

-- Get all subscribed communities
CREATE
OR REPLACE FUNCTION get_user_subscribed_communities(p_user_id uuid)
    RETURNS TABLE(
        id uuid,
        name text,
        description text,
        banner_url text,
        icon_url text,
        type community_type,
        is_mature boolean,
        created_by uuid,
        created_at timestamptz,
        updated_at timestamptz
    )
    AS $$
BEGIN
RETURN QUERY
SELECT c.id,
       c.name,
       c.description,
       c.banner_url,
       c.icon_url,
       c.type,
       c.is_mature,
       c.created_by,
       c.created_at,
       c.updated_at
FROM communities c
         INNER JOIN user_subscriptions us ON us.community_id = c.id
WHERE us.user_id = p_user_id;
END;
$$
LANGUAGE plpgsql;

-- +1 when user subscribes
CREATE
OR REPLACE FUNCTION increment_members_count()
    RETURNS TRIGGER
    AS $$
BEGIN
UPDATE
    communities
SET members_count = members_count + 1
WHERE id = NEW.community_id;
RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- -1 when user unsubscribes
CREATE
OR REPLACE FUNCTION decrement_members_count()
    RETURNS TRIGGER
    AS $$
BEGIN
UPDATE
    communities
SET members_count = GREATEST(members_count - 1, 0)
WHERE id = OLD.community_id;
RETURN OLD;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER trigger_increment_members_count
    AFTER INSERT
    ON user_subscriptions
    FOR EACH ROW
    EXECUTE FUNCTION increment_members_count();

CREATE TRIGGER trigger_decrement_members_count
    AFTER DELETE
    ON user_subscriptions
    FOR EACH ROW
    EXECUTE FUNCTION decrement_members_count();

