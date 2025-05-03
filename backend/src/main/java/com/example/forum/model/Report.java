package com.example.forum.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.forum.enums.ReportStatus;
import com.example.forum.enums.VoteTarget;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	@Id
	@Column(name = "id", nullable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "reporter_id", nullable = true)
	private User reporter;

	@Enumerated(EnumType.STRING)
	@Column(name = "target_type", nullable = false)
	private VoteTarget targetType;

	@Column(name = "target_id", nullable = false)
	private UUID targetId;

	@Column(name = "reason", columnDefinition = "TEXT", nullable = false)
	private String reason;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ReportStatus status = ReportStatus.PENDING;

	@ManyToOne
	@JoinColumn(name = "moderator_id", nullable = true)
	private User moderator;

	@Column(name = "resolution_note", columnDefinition = "TEXT", nullable = true)
	private String resolutionNote;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
}
