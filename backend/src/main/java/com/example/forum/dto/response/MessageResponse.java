package com.example.forum.dto.response;

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
public class MessageResponse {

    private String content;
    private Instant createdAt;
    private UUID id;
    private boolean read;
    private UUID recipientId;
    private UUID senderId;
    private String subject;
    private Instant updatedAt;
}
