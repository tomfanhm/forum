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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRole {

	@Builder.Default
	@Column(name = "can_administrate", nullable = false)
	private boolean canAdministrate = false;

	@Builder.Default
	@Column(name = "can_create_posts", nullable = false)
	private boolean canCreatePosts = true;

	@Builder.Default
	@Column(name = "can_create_threads", nullable = false)
	private boolean canCreateThreads = true;

	@Builder.Default
	@Column(name = "can_moderate", nullable = false)
	private boolean canModerate = false;

	@Builder.Default
	@Column(name = "can_report", nullable = false)
	private boolean canReport = true;

	@Builder.Default
	@Column(name = "can_upload_attachments", nullable = false)
	private boolean canUploadAttachments = true;

	@Builder.Default
	@Column(name = "can_vote", nullable = false)
	private boolean canVote = true;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@ToString.Include
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
}
