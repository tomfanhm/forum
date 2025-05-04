package com.example.forum.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.UserActivityLog;

@Repository
public interface UserActivityLogRepository extends JpaRepository<UserActivityLog, UUID> {
}
