package com.example.forum.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "community_topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(CommunityTopicId.class)
public class CommunityTopic {

    @Id
    @Column(name = "community_id")
    private UUID communityId;

    @Id
    @Column(name = "topic_id")
    private UUID topicId;
}