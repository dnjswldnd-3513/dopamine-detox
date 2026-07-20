package com.example.dopamine_detox.controller;

import com.example.dopamine_detox.dto.request.ChallengeRequest;
import com.example.dopamine_detox.dto.request.JoinChallengeRequest;
import com.example.dopamine_detox.dto.response.ChallengeResponse;
import com.example.dopamine_detox.security.CustomUserDetails;
import com.example.dopamine_detox.service.ChallengeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChallengeResponse> create(@Valid @RequestBody ChallengeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(challengeService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ChallengeResponse>> getAll() {
        return ResponseEntity.ok(challengeService.getAll());
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@AuthenticationPrincipal CustomUserDetails userDetails,
                                     @Valid @RequestBody JoinChallengeRequest request) {
        challengeService.join(userDetails.getMember().getId(), request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{challengeId}/complete")
    public ResponseEntity<Void> complete(@AuthenticationPrincipal CustomUserDetails userDetails,
                                         @PathVariable Long challengeId) {
        challengeService.complete(userDetails.getMember().getId(), challengeId);
        return ResponseEntity.ok().build();
    }
}
