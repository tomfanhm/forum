package com.example.forum.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
	List<Notification> findByUserId(UUID userId);
}