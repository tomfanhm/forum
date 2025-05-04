package com.example.forum.dto.response;

import java.time.Instant;
import java.util.UUID;

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
public class UserResponse {

	private String avatarUrl;
	private String bio;
	private Instant createdAt;
	private String displayName;
	private String email;
	private boolean emailVerified;
	private String firebaseUid;
	private UUID id;
	private boolean isActive;
	private Instant lastLoginAt;
	private String location;
	private int reputationPoints;
	private String role;
	private Instant updatedAt;
	private String website;
}
