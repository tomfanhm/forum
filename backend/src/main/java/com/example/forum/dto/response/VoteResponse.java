package com.example.forum.dto.response;

import java.time.Instant;
import java.util.UUID;

import com.example.forum.enums.VoteTarget;
import com.example.forum.enums.VoteValue;
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
public class VoteResponse {

	private Instant createdAt;
	private UUID id;
	private UUID targetId;
	private VoteTarget targetType;
	private Instant updatedAt;
	private UUID userId;
	private VoteValue value;
}
