package com.example.forum.controller;

import com.example.forum.dto.response.MessageResponse;
import com.example.forum.dto.response.TopicResponse;
import com.example.forum.exception.TopicNotFoundException;
import com.example.forum.mapper.TopicMapper;
import com.example.forum.model.Topic;
import com.example.forum.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/topic")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAllTopics() {
        List<Topic> topics = topicService.findAll();
        List<TopicResponse> response = TopicMapper.toTopicResponseList(topics);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<TopicResponse>> getTopicsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Topic> topics = topicService.findAll(pageable);
        Page<TopicResponse> response = topics.map(TopicMapper::toTopicResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<TopicResponse>> getPopularTopics() {
        List<Topic> topics = topicService.findAllOrderByCountDesc();
        List<TopicResponse> response = TopicMapper.toTopicResponseList(topics);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/popular/paginated")
    public ResponseEntity<Page<TopicResponse>> getPopularTopicsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Topic> topics = topicService.findAllOrderByCountDesc(pageable);
        Page<TopicResponse> response = topics.map(TopicMapper::toTopicResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getTopicById(@PathVariable UUID id) {
        Optional<Topic> topic = topicService.findById(id);
        if (topic.isEmpty()) {
            throw new TopicNotFoundException("Topic not found.");
        }
        TopicResponse response = TopicMapper.toTopicResponse(topic.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/count")
    public ResponseEntity<?> getTopicCount(@PathVariable UUID id) {
        Integer count = topicService.getTopicCount(id);
        return ResponseEntity.ok(Map.of("count", count));
    }

    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<TopicResponse>> getTopicsForCommunity(@PathVariable UUID communityId) {
        List<Topic> topics = topicService.getTopicsForCommunity(communityId);
        List<TopicResponse> response = TopicMapper.toTopicResponseList(topics);
        return ResponseEntity.ok(response);
    }

    // Delete topic by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteTopic(@PathVariable UUID id) {
        boolean deleted = topicService.deleteTopic(id);
        if (!deleted) {
            throw new TopicNotFoundException("Topic not found.");
        }
        return ResponseEntity.ok(new MessageResponse("Topic deleted successfully"));
    }
}
