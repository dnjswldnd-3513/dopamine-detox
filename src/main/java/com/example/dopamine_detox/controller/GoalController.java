package com.example.dopamine_detox.controller;

import com.example.dopamine_detox.dto.request.GoalRequest;
import com.example.dopamine_detox.dto.response.GoalResponse;
import com.example.dopamine_detox.security.CustomUserDetails;
import com.example.dopamine_detox.service.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<GoalResponse> create(@AuthenticationPrincipal CustomUserDetails userDetails,
                                               @Valid @RequestBody GoalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(goalService.create(userDetails.getMember().getId(), request));
    }

    @GetMapping
    public ResponseEntity<List<GoalResponse>> getMyGoals(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(goalService.getMyGoals(userDetails.getMember().getId()));
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal CustomUserDetails userDetails,
                                       @PathVariable Long goalId) {
        goalService.delete(goalId, userDetails.getMember().getId());
        return ResponseEntity.noContent().build();
    }
}
