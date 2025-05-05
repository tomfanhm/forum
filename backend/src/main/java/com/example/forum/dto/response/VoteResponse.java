package com.example.forum.dto.response;

import com.example.forum.enums.VoteTarget;
import com.example.forum.enums.VoteValue;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteResponse {

    private Instant createdAt;
    private UUID id;
    private UUID targetId;
    private VoteTarget targetType;
    private Instant updatedAt;
    private UUID userId;
    private VoteValue value;
}
