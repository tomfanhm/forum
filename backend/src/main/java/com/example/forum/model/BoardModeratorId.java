package com.example.forum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardModeratorId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer board;

    private java.util.UUID user;
}
