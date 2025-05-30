CREATE
OR REPLACE FUNCTION validate_community_name(name text)
    RETURNS void
    AS $$
BEGIN
    IF
name !~ '^[a-z0-9_]+$' THEN
        RAISE EXCEPTION 'Community name must contain only lowercase letters, numbers, and underscores.';
END IF;
END;
$$
LANGUAGE plpgsql;

