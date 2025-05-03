package com.example.forum.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class ThreadWatchId implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID user;

	private UUID thread;
}