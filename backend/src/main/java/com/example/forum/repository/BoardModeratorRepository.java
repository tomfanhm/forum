package com.example.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.BoardModerator;
import com.example.forum.model.BoardModeratorId;

@Repository
public interface BoardModeratorRepository extends JpaRepository<BoardModerator, BoardModeratorId> {
}
