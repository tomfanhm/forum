package com.example.forum.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
