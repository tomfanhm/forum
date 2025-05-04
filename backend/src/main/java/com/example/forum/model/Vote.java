package com.example.forum.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.example.forum.enums.VoteTarget;
import com.example.forum.enums.VoteValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "votes", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "target_type", "target_id" }))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Id
	@UuidGenerator
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "target_id", nullable = false)
	private UUID targetId;

	@Enumerated(EnumType.STRING)
	@Column(name = "target_type", nullable = false)
	private VoteTarget targetType;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(name = "value", nullable = false)
	private VoteValue value;
}
