package com.example.forum.dto.response;

import com.example.forum.enums.CommunityType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityInformationResponse {
    private UUID id;
    private String name;
    private String description;
    private String bannerUrl;
    private String iconUrl;
    private CommunityType type;
    private boolean isMature;
    private int membersCount;
    private Instant createdAt;
    private List<String> topics;
    private UUID authorId;
    private String authorDisplayName;
    private String authorAvatarUrl;
}
