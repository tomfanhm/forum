package com.example.forum.repository;

import com.example.forum.model.User;
import com.example.forum.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, User> {
}
