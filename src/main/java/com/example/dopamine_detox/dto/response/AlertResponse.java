package com.example.dopamine_detox.dto.response;

import com.example.dopamine_detox.domain.Alert;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AlertResponse {
    private final Long id;
    private final String message;
    private final LocalDateTime createdAt;

    public AlertResponse(Alert alert) {
        this.id = alert.getId();
        this.message = alert.getMessage();
        this.createdAt = alert.getCreatedAt();
    }
}
