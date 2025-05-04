package com.example.forum.dto.response;

import java.time.Instant;
import java.util.UUID;

import com.example.forum.enums.NotificationType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {

	private Instant createdAt;
	private UUID id;
	private boolean read;
	private UUID referenceId;
	private NotificationType type;
	private Instant updatedAt;
	private UUID userId;
}
