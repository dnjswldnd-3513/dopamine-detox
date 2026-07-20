package com.example.dopamine_detox.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GoalRequest {

    @NotNull
    private Long appCategoryId;

    @Min(1)
    private int limitMinutes;
}
