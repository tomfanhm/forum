package com.example.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.BoardCategory;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Integer> {
}
