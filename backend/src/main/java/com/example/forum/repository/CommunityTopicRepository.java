package com.example.forum.repository;

import com.example.forum.model.CommunityTopic;
import com.example.forum.model.CommunityTopicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommunityTopicRepository extends JpaRepository<CommunityTopic, CommunityTopicId> {
    List<CommunityTopic> findByCommunityId(UUID communityId);

    List<CommunityTopic> findByTopicId(UUID topicId);

    boolean existsByCommunityIdAndTopicId(UUID communityId, UUID topicId);

    @Modifying
    @Query(value = """
            DELETE FROM community_topics WHERE community_id = :communityId
            """, nativeQuery = true)
    void deleteByCommunityId(@Param("communityId") UUID communityId);

    @Modifying
    @Query(value = """
            DELETE FROM community_topics WHERE topic_id = :topicId
            """, nativeQuery = true)
    void deleteByTopicId(@Param("topicId") UUID topicId);
}
