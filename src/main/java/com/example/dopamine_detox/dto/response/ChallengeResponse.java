package com.example.dopamine_detox.dto.response;

import com.example.dopamine_detox.domain.Challenge;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ChallengeResponse {
    private final Long id;
    private final String appCategory;
    private final String title;
    private final String description;
    private final int limitMinutes;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int bonusScore;

    public ChallengeResponse(Challenge challenge) {
        this.id = challenge.getId();
        this.appCategory = challenge.getAppCategory().getName();
        this.title = challenge.getTitle();
        this.description = challenge.getDescription();
        this.limitMinutes = challenge.getLimitMinutes();
        this.startDate = challenge.getStartDate();
        this.endDate = challenge.getEndDate();
        this.bonusScore = challenge.getBonusScore();
    }
}
