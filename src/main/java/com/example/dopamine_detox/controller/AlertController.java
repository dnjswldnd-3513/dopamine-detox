package com.example.dopamine_detox.controller;

import com.example.dopamine_detox.dto.response.AlertResponse;
import com.example.dopamine_detox.security.CustomUserDetails;
import com.example.dopamine_detox.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @GetMapping
    public ResponseEntity<List<AlertResponse>> getMyAlerts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(alertService.getMyAlerts(userDetails.getMember().getId()));
    }
}
