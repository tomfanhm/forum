CREATE
OR REPLACE FUNCTION create_user(p_firebase_uid text, p_display_name text DEFAULT NULL, p_avatar_url text DEFAULT NULL, p_bio text DEFAULT NULL, p_location text DEFAULT NULL, p_website text DEFAULT NULL, p_birthday date DEFAULT NULL, p_gender gender DEFAULT NULL, p_email text DEFAULT NULL)
    RETURNS uuid
    AS $$
DECLARE
v_user_id uuid;
BEGIN
INSERT INTO users(firebase_uid, display_name, avatar_url, bio, location, website, birthday, gender, email)
VALUES (p_firebase_uid, p_display_name, p_avatar_url, p_bio, p_location, p_website, p_birthday, p_gender, p_email) RETURNING
        id
INTO v_user_id;
RETURN v_user_id;
END;
$$
LANGUAGE plpgsql;

