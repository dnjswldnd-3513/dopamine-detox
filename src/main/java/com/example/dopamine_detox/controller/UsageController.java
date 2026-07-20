package com.example.dopamine_detox.controller;

import com.example.dopamine_detox.dto.request.UsageStartRequest;
import com.example.dopamine_detox.dto.request.UsageStopRequest;
import com.example.dopamine_detox.dto.response.UsageResponse;
import com.example.dopamine_detox.security.CustomUserDetails;
import com.example.dopamine_detox.service.UsageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage")
@RequiredArgsConstructor
public class UsageController {

    private final UsageService usageService;

    @PostMapping("/start")
    public ResponseEntity<UsageResponse> start(@AuthenticationPrincipal CustomUserDetails userDetails,
                                               @Valid @RequestBody UsageStartRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usageService.start(userDetails.getMember().getId(), request));
    }

    @PostMapping("/stop")
    public ResponseEntity<UsageResponse> stop(@AuthenticationPrincipal CustomUserDetails userDetails,
                                              @Valid @RequestBody UsageStopRequest request) {
        return ResponseEntity.ok(usageService.stop(userDetails.getMember().getId(), request));
    }

    @GetMapping
    public ResponseEntity<List<UsageResponse>> getMyUsage(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(usageService.getMyUsage(userDetails.getMember().getId()));
    }
}
