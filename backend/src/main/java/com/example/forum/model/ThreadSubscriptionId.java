package com.example.forum.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadSubscriptionId implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID thread;

	private UUID user;
}
