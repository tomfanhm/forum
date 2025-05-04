package com.example.forum.dto.response;

import java.time.Instant;

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
public class ForumStatisticResponse {

	private Long activeUsersLastDay;
	private Long activeUsersLastMonth;
	private Long activeUsersLastWeek;
	private Long id;
	private Instant lastCalculatedAt;
	private Long totalPosts;
	private Long totalThreads;
	private Long totalUsers;
	private Instant updatedAt;
}
