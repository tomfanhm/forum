package com.example.forum.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(ThreadWatchId.class)
@Table(name = "thread_watches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThreadWatch {

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Builder.Default
	@Column(name = "last_read_at", nullable = false)
	private Instant lastReadAt = Instant.now();

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "thread_id", nullable = false)
	private Thread thread;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
