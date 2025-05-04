package com.example.forum.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPreference {

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Builder.Default
	@Column(name = "dark_mode", nullable = false)
	private boolean darkMode = false;

	@Builder.Default
	@Column(name = "notification_emails", nullable = false)
	private boolean notificationEmails = true;

	@Builder.Default
	@Column(name = "show_avatars", nullable = false)
	private boolean showAvatars = true;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	@Id
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
