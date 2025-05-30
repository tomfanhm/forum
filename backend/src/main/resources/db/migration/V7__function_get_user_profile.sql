CREATE
OR REPLACE FUNCTION get_user_profile(p_firebase_uid text)
    RETURNS TABLE(
        id uuid,
        firebase_uid text,
        display_name text,
        avatar_url text,
        bio text,
        location text,
        website text,
        birthday date,
        gender gender,
        email text,
        email_verified boolean,
        is_active boolean,
        is_banned boolean,
        banned_at timestamptz,
        reputation_points integer,
        created_at timestamptz,
        updated_at timestamptz
    )
    AS $$
BEGIN
RETURN QUERY
SELECT u.id,
       u.firebase_uid,
       u.display_name,
       u.avatar_url,
       u.bio,
       u.location,
       u.website,
       u.birthday,
       u.gender,
       u.email,
       u.email_verified,
       u.is_active,
       u.is_banned,
       u.banned_at,
       u.reputation_points,
       u.created_at,
       u.updated_at
FROM users u
WHERE u.firebase_uid = p_firebase_uid;
END;
$$
LANGUAGE plpgsql;

