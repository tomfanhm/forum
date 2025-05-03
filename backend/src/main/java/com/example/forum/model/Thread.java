package com.example.forum.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "threads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Thread {

	@Id
	@Column(name = "id", nullable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "body", columnDefinition = "TEXT", nullable = false)
	private String body;

	@Column(name = "is_pinned", nullable = false)
	private boolean pinned = false;

	@Column(name = "is_locked", nullable = false)
	private boolean locked = false;

	@Column(name = "comment_count", nullable = false)
	private Integer commentCount = 0;

	@Column(name = "view_count", nullable = false)
	private Long viewCount = 0L;

	@Column(name = "search_vector", columnDefinition = "tsvector", nullable = true)
	private String searchVector;

	@Column(name = "is_deleted", nullable = false)
	private boolean deleted = false;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
}
