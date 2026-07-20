package com.example.dopamine_detox.dto.response;

import com.example.dopamine_detox.domain.UsageRecord;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UsageResponse {
    private final Long id;
    private final String appCategory;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final int durationMinutes;

    public UsageResponse(UsageRecord record) {
        this.id = record.getId();
        this.appCategory = record.getAppCategory().getName();
        this.startTime = record.getStartTime();
        this.endTime = record.getEndTime();
        this.durationMinutes = record.getDurationMinutes();
    }
}
