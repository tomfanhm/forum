package com.example.forum.mapper;

import com.example.forum.dto.response.TopicResponse;
import com.example.forum.model.Topic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicMapper {

    public static TopicResponse toTopicResponse(Topic topic) {
        if (topic == null) {
            return null;
        }

        return TopicResponse.builder()
                .id(topic.getId())
                .name(topic.getName())
                .count(topic.getCount())
                .build();
    }

    public static List<TopicResponse> toTopicResponseList(List<Topic> topics) {
        if (topics == null) {
            return null;
        }

        return topics.stream()
                .map(TopicMapper::toTopicResponse)
                .collect(Collectors.toList());
    }
}
