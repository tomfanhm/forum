package com.example.forum.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@IdClass(ThreadSubscriptionId.class)
@Table(name = "thread_subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThreadSubscription {

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name = "thread_id", nullable = false)
	private Thread thread;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
}