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
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "firebase_uid", nullable = false, unique = true)
	private String firebaseUid;

	@Column(name = "display_name", nullable = true, length = 50)
	private String displayName;

	@Column(name = "avatar_url", nullable = true)
	private String avatarUrl;

	@Column(name = "role", nullable = false)
	private String role = "USER";

	@Column(name = "bio", columnDefinition = "TEXT", nullable = true)
	private String bio;

	@Column(name = "location", nullable = true)
	private String location;

	@Column(name = "website", nullable = true)
	private String website;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "email_verified", nullable = false)
	private boolean emailVerified = false;

	@Column(name = "last_login_at", nullable = true)
	private Instant lastLoginAt;

	@Column(name = "is_active", nullable = false)
	private boolean isActive = true;

	@Column(name = "reputation_points", nullable = false)
	private Integer reputationPoints = 0;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = true)
	private UserRole userRole;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
}
