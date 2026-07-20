package com.example.dopamine_detox.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ChallengeRequest {

    @NotNull
    private Long appCategoryId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Min(1)
    private int limitMinutes;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Min(1)
    private int bonusScore;
}
