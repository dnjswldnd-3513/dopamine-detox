package com.example.dopamine_detox.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UsageStopRequest {

    @NotNull
    private Long usageRecordId;
}
