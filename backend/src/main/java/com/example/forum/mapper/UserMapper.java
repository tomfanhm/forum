package com.example.forum.mapper;

import com.example.forum.dto.response.ProfileResponse;
import com.example.forum.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static ProfileResponse toProfileResponse(User user) {
        if (user == null) {
            return null;
        }

        return ProfileResponse.builder()
                .displayName(user.getDisplayName())
                .avatarUrl(user.getAvatarUrl())
                .bio(user.getBio())
                .website(user.getWebsite())
                .location(user.getLocation())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .build();
    }
}