package com.example.forum.repository;

import com.example.forum.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
    Optional<Topic> findByName(String name);

    boolean existsByName(String name);

    List<Topic> findAllByOrderByCountDesc();

    Page<Topic> findAllByOrderByCountDesc(Pageable pageable);

    @Query(
            value = """
                    SELECT t.*
                    FROM topics t
                    JOIN community_topics ct ON t.id = ct.topic_id
                    WHERE ct.community_id = :communityId
                    """,
            nativeQuery = true)
    List<Topic> findTopicsByCommunityId(@Param("communityId") UUID communityId);

    @Query(value = """
            SELECT t FROM topics t ORDER BY t.count DESC LIMIT :limit
            """, nativeQuery = true)
    List<Topic> findTopByOrderByCountDesc(@Param("limit") int limit);
}