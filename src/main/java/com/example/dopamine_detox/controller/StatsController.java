package com.example.dopamine_detox.controller;

import com.example.dopamine_detox.dto.response.StatsResponse;
import com.example.dopamine_detox.security.CustomUserDetails;
import com.example.dopamine_detox.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/today")
    public ResponseEntity<List<StatsResponse>> getTodayStats(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(statsService.getTodayStats(userDetails.getMember().getId()));
    }
}
