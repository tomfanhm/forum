package com.example.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forum.model.ThreadSubscription;
import com.example.forum.model.ThreadSubscriptionId;

@Repository
public interface ThreadSubscriptionRepository extends JpaRepository<ThreadSubscription, ThreadSubscriptionId> {
}
