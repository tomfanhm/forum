package com.example.forum.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.Instant;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPreferenceResponse {

    private Instant createdAt;
    private boolean darkMode;
    private boolean notificationEmails;
    private boolean showAvatars;
    private Instant updatedAt;
    private String userId;
}
