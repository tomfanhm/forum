package com.example.forum.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
	List<Post> findByThreadId(UUID threadId);
}