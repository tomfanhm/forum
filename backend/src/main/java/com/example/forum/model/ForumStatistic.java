package com.example.forum.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "forum_statistics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForumStatistic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "total_users", nullable = false)
	private Long totalUsers = 0L;

	@Column(name = "total_threads", nullable = false)
	private Long totalThreads = 0L;

	@Column(name = "total_posts", nullable = false)
	private Long totalPosts = 0L;

	@Column(name = "active_users_last_day", nullable = false)
	private Long activeUsersLastDay = 0L;

	@Column(name = "active_users_last_week", nullable = false)
	private Long activeUsersLastWeek = 0L;

	@Column(name = "active_users_last_month", nullable = false)
	private Long activeUsersLastMonth = 0L;

	@CreationTimestamp
	@Column(name = "last_calculated_at", nullable = false, updatable = false)
	private Instant lastCalculatedAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
}