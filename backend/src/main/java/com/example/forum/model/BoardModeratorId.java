
package com.example.forum.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class BoardModeratorId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer board;

	private java.util.UUID user;
}