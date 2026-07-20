package com.example.dopamine_detox.dto.response;

import com.example.dopamine_detox.domain.Score;
import com.example.dopamine_detox.domain.ScoreReason;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScoreResponse {
    private final Long id;
    private final int point;
    private final ScoreReason reason;
    private final LocalDateTime createdAt;

    public ScoreResponse(Score score) {
        this.id = score.getId();
        this.point = score.getPoint();
        this.reason = score.getReason();
        this.createdAt = score.getCreatedAt();
    }
}
