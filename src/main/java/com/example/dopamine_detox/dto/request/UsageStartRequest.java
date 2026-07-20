package com.example.dopamine_detox.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UsageStartRequest {

    @NotNull
    private Long appCategoryId;
}
