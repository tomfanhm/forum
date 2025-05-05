package com.example.forum.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThreadResponse {

    private Integer boardId;
    private String body;
    private int commentCount;
    private Instant createdAt;
    private boolean deleted;
    private UUID id;
    private boolean locked;
    private boolean pinned;
    private List<TagResponse> tags;
    private String title;
    private Instant updatedAt;
    private UUID userId;
    private long viewCount;
}
