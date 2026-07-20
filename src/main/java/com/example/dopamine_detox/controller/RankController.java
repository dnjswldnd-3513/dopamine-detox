package com.example.dopamine_detox.controller;

import com.example.dopamine_detox.dto.response.RankResponse;
import com.example.dopamine_detox.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranks")
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;

    @GetMapping
    public ResponseEntity<List<RankResponse>> getRanking() {
        return ResponseEntity.ok(rankService.getRanking());
    }
}
