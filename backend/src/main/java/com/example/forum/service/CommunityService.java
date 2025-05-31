package com.example.forum.service;

import com.example.forum.dto.request.CreateCommunityRequest;
import com.example.forum.dto.request.UpdateCommunityInformationRequest;
import com.example.forum.dto.response.CommunityInformationResponse;
import com.example.forum.exception.AccessDeniedException;
import com.example.forum.exception.CommunityAlreadyExistsException;
import com.example.forum.exception.CommunityNotFoundException;
import com.example.forum.exception.UserNotFoundException;
import com.example.forum.mapper.CommunityMapper;
import com.example.forum.model.Community;
import com.example.forum.model.CommunityTopic;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.repository.CommunityRepository;
import com.example.forum.repository.CommunityTopicRepository;
import com.example.forum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final UserRepository userRepository;
    private final CommunityTopicRepository communityTopicRepository;
    private final CommunityRepository communityRepository;
    private final TopicService topicService;

    public boolean communityExists(String name) {
        return communityRepository.existsByName(name);
    }

    @Transactional
    public List<Topic> addTopicsToCommunity(UUID communityId, Set<String> topicNames) {
        Set<String> normalizeTopicNames = topicService.normalize(topicNames);
        if (normalizeTopicNames.isEmpty()) {
            return List.of();
        }
        // Get or create topics
        List<Topic> topics = topicService.getOrCreateTopics(normalizeTopicNames);
        for (Topic topic : topics) {
            if (!communityTopicRepository.existsByCommunityIdAndTopicId(communityId, topic.getId())) {
                CommunityTopic communityTopic = new CommunityTopic();
                communityTopic.setCommunityId(communityId);
                communityTopic.setTopicId(topic.getId());
                communityTopicRepository.save(communityTopic);
            }
        }
        return topics;
    }

    @Transactional
    public CommunityInformationResponse createCommunity(String uid, CreateCommunityRequest createCommunityRequest) {
        if (communityExists(createCommunityRequest.getName())) {
            throw new CommunityAlreadyExistsException("Community with this name already exists.");
        }

        User user = userRepository.findByFirebaseUid(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        // Create a new community
        Community community = new Community();
        community.setName(createCommunityRequest.getName());
        community.setDescription(createCommunityRequest.getDescription());
        community.setBannerUrl(createCommunityRequest.getBannerUrl());
        community.setIconUrl(createCommunityRequest.getIconUrl());
        community.setType(createCommunityRequest.getType());
        community.setMature(createCommunityRequest.isMature());
        community.setCreatedBy(user.getId());
        community.setMembersCount(0);
        community.setCreatedAt(Instant.now());

        // Save the community to the repository
        community = communityRepository.save(community);

        // Handle topics
        List<Topic> topics = addTopicsToCommunity(
                community.getId(),
                new HashSet<>(createCommunityRequest.getTopics())
        );

        return CommunityMapper.toCommunityInformationResponse(user, community, topics);
    }

    @Transactional(readOnly = true)
    public CommunityInformationResponse getCommunityInformation(String communityName) {
        Community community = communityRepository.findByName(communityName)
                .orElseThrow(() -> new CommunityNotFoundException("Community not found."));

        User user = userRepository.findById(community.getCreatedBy())
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        // Fetch topics associated with the community
        List<Topic> topics = topicService.getTopicsForCommunity(community.getId());

        return CommunityMapper.toCommunityInformationResponse(user, community, topics);
    }

    @Transactional(readOnly = true)
    public CommunityInformationResponse getCommunityInformationById(UUID id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new CommunityNotFoundException("Community not found."));

        User user = userRepository.findById(community.getCreatedBy())
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        // Fetch topics associated with the community
        List<Topic> topics = topicService.getTopicsForCommunity(community.getId());

        return CommunityMapper.toCommunityInformationResponse(user, community, topics);
    }

    @Transactional
    public CommunityInformationResponse updateCommunity(String uid, UUID id, UpdateCommunityInformationRequest request) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new CommunityNotFoundException("Community not found."));

        User user = userRepository.findByFirebaseUid(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        // Check if the user is the creator of the community
        if (!community.getCreatedBy().equals(user.getId())) {
            throw new AccessDeniedException("You are not authorized to update this community.");
        }

        // Update community details
        community.setDescription(request.getDescription());
        community.setBannerUrl(request.getBannerUrl());
        community.setIconUrl(request.getIconUrl());
        community.setType(request.getType());
        community.setMature(request.isMature());

        // Save the updated community
        community = communityRepository.save(community);

        // Handle topic updates
        communityTopicRepository.deleteByCommunityId(community.getId());
        List<Topic> topics = addTopicsToCommunity(
                community.getId(),
                new HashSet<>(request.getTopics())
        );

        return CommunityMapper.toCommunityInformationResponse(user, community, topics);
    }
}
