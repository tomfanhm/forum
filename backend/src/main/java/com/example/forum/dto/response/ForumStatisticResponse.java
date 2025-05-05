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
