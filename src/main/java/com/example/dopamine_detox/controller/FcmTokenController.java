package com.example.dopamine_detox.controller;

import com.example.dopamine_detox.dto.request.FcmTokenRequest;
import com.example.dopamine_detox.security.CustomUserDetails;
import com.example.dopamine_detox.service.FcmTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fcm-tokens")
@RequiredArgsConstructor
public class FcmTokenController {

    private final FcmTokenService fcmTokenService;

    @PostMapping
    public ResponseEntity<Void> save(@AuthenticationPrincipal CustomUserDetails userDetails,
                                     @Valid @RequestBody FcmTokenRequest request) {
        fcmTokenService.saveToken(userDetails.getMember().getId(), request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal CustomUserDetails userDetails,
                                       @RequestParam String token) {
        fcmTokenService.deleteToken(userDetails.getMember().getId(), token);
        return ResponseEntity.noContent().build();
    }
}
