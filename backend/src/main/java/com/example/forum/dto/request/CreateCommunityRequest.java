package com.example.forum.dto.request;

import com.example.forum.enums.CommunityType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCommunityRequest {
    @NotBlank
    @Size(min = 1, max = 255, message = "Community name must be between 1 and 255 characters")
    private String name;

    @Size(max = 1000, message = "Description must be at most 1000 characters")
    private String description;

    @URL(message = "Banner URL must be a valid URL")
    @Size(max = 2048, message = "Banner URL is too long")
    private String bannerUrl;

    @URL(message = "Icon URL must be a valid URL")
    @Size(max = 2048, message = "Icon URL is too long")
    private String iconUrl;

    @NotNull
    private CommunityType type;

    @NotNull
    private boolean isMature;

    @NotNull(message = "Topics list cannot be null")
    @Size(min = 1, max = 5, message = "You must provide between 1 and 5 topics")
    private List<@NotBlank @Size(max = 30, message = "Each topic must be at most 30 characters") String> topics;
}
