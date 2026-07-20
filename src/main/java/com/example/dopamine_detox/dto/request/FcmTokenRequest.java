package com.example.dopamine_detox.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FcmTokenRequest {

    @NotBlank
    private String token;
}
