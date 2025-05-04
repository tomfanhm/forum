package com.example.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.ForumStatistic;

@Repository
public interface ForumStatisticRepository extends JpaRepository<ForumStatistic, Long> {
}
