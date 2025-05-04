package com.example.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.ThreadUserAlias;
import com.example.forum.model.ThreadUserAliasId;

@Repository
public interface ThreadUserAliasRepository extends JpaRepository<ThreadUserAlias, ThreadUserAliasId> {
}
