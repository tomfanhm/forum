CREATE
OR REPLACE FUNCTION create_community(p_name text, p_description text DEFAULT NULL, p_banner_url text DEFAULT NULL, p_icon_url text DEFAULT NULL, p_type community_type DEFAULT 'PUBLIC', p_is_mature boolean DEFAULT FALSE, p_created_by uuid)
    RETURNS uuid
    AS $$
DECLARE
v_id uuid;
BEGIN
    PERFORM
validate_community_name(p_name);
INSERT INTO communities(name, description, banner_url, icon_url, type, is_mature, created_by)
VALUES (p_name, p_description, p_banner_url, p_icon_url, p_type, p_is_mature, p_created_by) RETURNING
        id
INTO v_id;
RETURN v_id;
END;
$$
LANGUAGE plpgsql;

CREATE
OR REPLACE FUNCTION update_community(p_id uuid, p_description text DEFAULT NULL, p_banner_url text DEFAULT NULL, p_icon_url text DEFAULT NULL, p_type community_type DEFAULT NULL, p_is_mature boolean DEFAULT NULL)
    RETURNS void
    AS $$
BEGIN
UPDATE
    communities
SET description = COALESCE(p_description, description),
    banner_url  = COALESCE(p_banner_url, banner_url),
    icon_url    = COALESCE(p_icon_url, icon_url),
    type        = COALESCE(p_type, type),
    is_mature   = COALESCE(p_is_mature, is_mature)
WHERE id = p_id;
END;
$$
LANGUAGE plpgsql;

CREATE
OR REPLACE FUNCTION get_community(p_id uuid)
    RETURNS TABLE(
        id uuid,
        name text,
        description text,
        banner_url text,
        icon_url text,
        type community_type,
        is_mature boolean,
        created_by uuid,
        members_count integer,
        created_at timestamptz,
        updated_at timestamptz
    )
    AS $$
BEGIN
RETURN QUERY
SELECT id,
       name,
       description,
       banner_url,
       icon_url,
       type,
       is_mature,
       created_by,
       members_count,
       created_at,
       updated_at
FROM communities
WHERE id = p_id;
END;
$$
LANGUAGE plpgsql;

CREATE
OR REPLACE FUNCTION delete_community(p_id uuid)
    RETURNS void
    AS $$
BEGIN
DELETE
FROM communities
WHERE id = p_id;
END;
$$
LANGUAGE plpgsql;

