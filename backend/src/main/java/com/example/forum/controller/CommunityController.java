package com.example.forum.controller;

import com.example.forum.dto.request.CreateCommunityRequest;
import com.example.forum.dto.request.UpdateCommunityInformationRequest;
import com.example.forum.dto.response.CommunityInformationResponse;
import com.example.forum.security.FirebaseUserDetails;
import com.example.forum.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping("/create")
    public ResponseEntity<CommunityInformationResponse> createCommunity(@AuthenticationPrincipal FirebaseUserDetails userDetails, @Valid @RequestBody CreateCommunityRequest request) {
        String uid = userDetails.getUid();
        CommunityInformationResponse communityInformationResponse = communityService.createCommunity(uid, request);
        return ResponseEntity.ok(communityInformationResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommunityInformationResponse> updateCommunityInformation(@AuthenticationPrincipal FirebaseUserDetails userDetails,
                                                        @PathVariable UUID id,
                                                        @Valid @RequestBody UpdateCommunityInformationRequest request) {
        String uid = userDetails.getUid();
        CommunityInformationResponse communityInformationResponse = communityService.updateCommunity(uid, id, request);
        return ResponseEntity.ok(communityInformationResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityInformationResponse> getCommunityInformation(@PathVariable UUID id) {
        CommunityInformationResponse communityInformationResponse = communityService.getCommunityInformationById(id);
        return ResponseEntity.ok(communityInformationResponse);
    }

}
