package com.example.forum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadTagId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tag;

    private UUID thread;
}
