package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.Member;
import com.example.dopamine_detox.domain.Score;
import com.example.dopamine_detox.domain.ScoreReason;
import com.example.dopamine_detox.dto.response.ScoreResponse;
import com.example.dopamine_detox.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScoreService {

    private final ScoreRepository scoreRepository;

    @Transactional
    public void addScore(Member member, int point, ScoreReason reason) {
        Score score = Score.builder()
                .member(member)
                .point(point)
                .reason(reason)
                .createdAt(LocalDateTime.now())
                .build();
        scoreRepository.save(score);
    }

    public List<ScoreResponse> getMyScores(Long memberId) {
        return scoreRepository.findByMemberId(memberId).stream()
                .map(ScoreResponse::new)
                .toList();
    }

    public int getMyTotalScore(Long memberId) {
        return scoreRepository.findByMemberId(memberId).stream()
                .mapToInt(Score::getPoint)
                .sum();
    }
}
