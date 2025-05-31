package com.example.forum.mapper;

import com.example.forum.dto.response.CommunityInformationResponse;
import com.example.forum.model.Community;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommunityMapper {
    public static CommunityInformationResponse toCommunityInformationResponse(User user, Community community, List<Topic> topics) {
        if (user == null || community == null || topics == null) {
            return null;
        }

        return CommunityInformationResponse.builder()
                .id(community.getId())
                .name(community.getName())
                .description(community.getDescription())
                .bannerUrl(community.getBannerUrl())
                .iconUrl(community.getIconUrl())
                .type(community.getType())
                .isMature(community.isMature())
                .membersCount(community.getMembersCount())
                .createdAt(community.getCreatedAt())
                .authorId(user.getId())
                .authorDisplayName(user.getDisplayName())
                .authorAvatarUrl(user.getAvatarUrl())
                .topics(topics.stream().map(Topic::getName).toList())
                .build();
    }
}

