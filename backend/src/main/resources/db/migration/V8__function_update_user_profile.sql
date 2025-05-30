CREATE
OR REPLACE FUNCTION update_user_profile(p_firebase_uid text, p_display_name text DEFAULT NULL, p_avatar_url text DEFAULT NULL, p_bio text DEFAULT NULL, p_location text DEFAULT NULL, p_website text DEFAULT NULL, p_birthday date DEFAULT NULL, p_gender gender DEFAULT NULL, p_email text DEFAULT NULL)
    RETURNS void
    AS $$
BEGIN
UPDATE
    users
SET display_name = COALESCE(p_display_name, display_name),
    avatar_url   = COALESCE(p_avatar_url, avatar_url),
    bio          = COALESCE(p_bio, bio),
    location     = COALESCE(p_location, location),
    website      = COALESCE(p_website, website),
    birthday     = COALESCE(p_birthday, birthday),
    gender       = COALESCE(p_gender, gender),
    email        = COALESCE(p_email, email)
WHERE firebase_uid = p_firebase_uid;
END;
$$
LANGUAGE plpgsql;

