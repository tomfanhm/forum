package com.example.forum.repository;

import com.example.forum.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CommunityRepository extends JpaRepository<Community, UUID> {
    boolean existsByName(String name);

    Optional<Community> findByName(String name);
}