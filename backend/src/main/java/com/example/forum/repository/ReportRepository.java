package com.example.forum.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {
}
