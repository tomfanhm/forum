package com.example.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.ThreadWatch;
import com.example.forum.model.ThreadWatchId;

@Repository
public interface ThreadWatchRepository extends JpaRepository<ThreadWatch, ThreadWatchId> {
}
