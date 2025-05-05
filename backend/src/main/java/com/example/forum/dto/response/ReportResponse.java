package com.example.forum.dto.response;

import com.example.forum.enums.ReportStatus;
import com.example.forum.enums.VoteTarget;
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
public class ReportResponse {

    private Instant createdAt;
    private UUID id;
    private UUID moderatorId;
    private String reason;
    private UUID reporterId;
    private String resolutionNote;
    private ReportStatus status;
    private UUID targetId;
    private VoteTarget targetType;
    private Instant updatedAt;
}
