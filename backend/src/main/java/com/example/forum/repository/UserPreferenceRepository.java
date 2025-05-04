package com.example.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.User;
import com.example.forum.model.UserPreference;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, User> {
}
