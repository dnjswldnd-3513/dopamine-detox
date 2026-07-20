package com.example.dopamine_detox.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatsResponse {
    private final String appCategory;
    private final int totalMinutes;
    private final int goalMinutes;
    private final boolean goalExceeded;
}
