package com.example.forum.service;

import com.example.forum.dto.request.UpdateProfileRequest;
import com.example.forum.dto.response.ProfileResponse;
import com.example.forum.exception.UserNotFoundException;
import com.example.forum.mapper.UserMapper;
import com.example.forum.model.User;
import com.example.forum.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FirebaseAuth firebaseAuth;

    @Transactional(readOnly = true)
    public ProfileResponse getProfile(String uid) {
        User user = userRepository.findByFirebaseUid(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        return UserMapper.toProfileResponse(user);
    }

    @Transactional
    public ProfileResponse updateProfile(String uid, UpdateProfileRequest request) {
        User user = userRepository.findByFirebaseUid(uid)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        user.setDisplayName(request.getDisplayName());
        user.setAvatarUrl(request.getAvatarUrl());
        user.setBio(request.getBio());
        user.setWebsite(request.getWebsite());
        user.setLocation(request.getLocation());
        user.setBirthday(request.getBirthday());
        user.setGender(request.getGender());
        userRepository.save(user);
        return UserMapper.toProfileResponse(user);
    }

}
