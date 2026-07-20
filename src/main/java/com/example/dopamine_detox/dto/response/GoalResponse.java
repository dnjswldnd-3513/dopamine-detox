package com.example.dopamine_detox.dto.response;

import com.example.dopamine_detox.domain.Goal;
import lombok.Getter;

@Getter
public class GoalResponse {
    private final Long id;
    private final String appCategory;
    private final int limitMinutes;

    public GoalResponse(Goal goal) {
        this.id = goal.getId();
        this.appCategory = goal.getAppCategory().getName();
        this.limitMinutes = goal.getLimitMinutes();
    }
}
