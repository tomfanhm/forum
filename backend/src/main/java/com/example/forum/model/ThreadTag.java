package com.example.forum.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

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
@IdClass(ThreadTagId.class)
@Table(name = "thread_tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThreadTag {

	@Id
	@ManyToOne
	@JoinColumn(name = "thread_id", nullable = false)
	private Thread thread;

	@Id
	@ManyToOne
	@JoinColumn(name = "tag_id", nullable = false)
	private Tag tag;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;
}