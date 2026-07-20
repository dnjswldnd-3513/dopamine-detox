package com.example.dopamine_detox.controller;

import com.example.dopamine_detox.dto.response.ScoreResponse;
import com.example.dopamine_detox.security.CustomUserDetails;
import com.example.dopamine_detox.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping
    public ResponseEntity<List<ScoreResponse>> getMyScores(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(scoreService.getMyScores(userDetails.getMember().getId()));
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Integer>> getMyTotal(@AuthenticationPrincipal CustomUserDetails userDetails) {
        int total = scoreService.getMyTotalScore(userDetails.getMember().getId());
        return ResponseEntity.ok(Map.of("totalScore", total));
    }
}
