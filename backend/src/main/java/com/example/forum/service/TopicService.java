package com.example.forum.service;

import com.example.forum.exception.TopicAlreadyExistsException;
import com.example.forum.model.Topic;
import com.example.forum.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    public Integer getTopicCount(UUID topicId) {
        return topicRepository.findById(topicId)
                .map(Topic::getCount)
                .orElse(0);
    }

    public Integer getTopicCountByName(String name) {
        return topicRepository.findByName(name)
                .map(Topic::getCount)
                .orElse(0);
    }

    public Optional<UUID> getIdByName(String name) {
        return topicRepository.findByName(name)
                .map(Topic::getId);
    }

    public Optional<Topic> findByName(String name) {
        return topicRepository.findByName(name);
    }

    public Optional<Topic> findById(UUID id) {
        return topicRepository.findById(id);
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public Page<Topic> findAll(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    // Get topics ordered by count (most popular first)
    public List<Topic> findAllOrderByCountDesc() {
        return topicRepository.findAllByOrderByCountDesc();
    }

    // Get topics ordered by count with pagination
    public Page<Topic> findAllOrderByCountDesc(Pageable pageable) {
        return topicRepository.findAllByOrderByCountDesc(pageable);
    }

    // Get top N topics by count
    public List<Topic> findTopTopicsByCount(int limit) {
        return topicRepository.findTopByOrderByCountDesc(limit);
    }

    @Transactional
    public Topic getOrCreateTopic(String name) {
        return topicRepository.findByName(name)
                .orElseGet(() -> createTopic(name));
    }

    @Transactional
    public List<Topic> getOrCreateTopics(Set<String> topicNames) {
        return topicNames.stream()
                .map(this::getOrCreateTopic)
                .collect(Collectors.toList());
    }

    @Transactional
    public Topic createTopic(String name) {
        if (topicRepository.existsByName(name)) {
            throw new TopicAlreadyExistsException("Topic already exists.");
        }

        Topic topic = Topic.builder()
                .name(name)
                .count(0)
                .build();

        return topicRepository.save(topic);
    }

    public List<Topic> findByIds(Set<UUID> topicIds) {
        return topicRepository.findAllById(topicIds);
    }

    @Transactional
    public boolean deleteTopic(UUID id) {
        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsByName(String name) {
        return topicRepository.existsByName(name);
    }

    public boolean existsById(UUID id) {
        return topicRepository.existsById(id);
    }

    public List<Topic> getTopicsForCommunity(UUID communityId) {
        return topicRepository.findTopicsByCommunityId(communityId);
    }

    public Set<String> normalize(Set<String> topicNames) {
        if (topicNames == null) {
            return Set.of();
        }

        return topicNames.stream()
                .filter(name -> name != null && !name.trim().isEmpty())
                .map(String::trim)
                .map(String::toLowerCase) // Normalize to lowercase
                .collect(Collectors.toSet());
    }
}
