package com.example.forum.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "thread_views")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThreadView {

	@Id
	@Column(name = "id", nullable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "thread_id", nullable = false)
	private Thread thread;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	@Column(name = "ip_address", nullable = true)
	private String ipAddress;

	@Column(name = "user_agent", nullable = true)
	private String userAgent;

	@CreationTimestamp
	@Column(name = "viewed_at", nullable = false, updatable = false)
	private Instant viewedAt;
}
