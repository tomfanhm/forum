package com.example.forum.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByFirebaseUid(String firebaseUid);
}
