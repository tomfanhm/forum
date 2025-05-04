package com.example.forum.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.example.forum.enums.ReportStatus;
import com.example.forum.enums.VoteTarget;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Id
	@UuidGenerator
	@Column(name = "id", nullable = false)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moderator_id", nullable = true)
	private User moderator;

	@Column(name = "reason", columnDefinition = "TEXT", nullable = false)
	private String reason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reporter_id", nullable = true)
	private User reporter;

	@Column(name = "resolution_note", columnDefinition = "TEXT", nullable = true)
	private String resolutionNote;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ReportStatus status = ReportStatus.PENDING;

	@Column(name = "target_id", nullable = false)
	private UUID targetId;

	@Enumerated(EnumType.STRING)
	@Column(name = "target_type", nullable = false)
	private VoteTarget targetType;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
}
