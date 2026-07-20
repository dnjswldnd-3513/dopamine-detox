package com.example.dopamine_detox.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankResponse {
    private final int rank;
    private final String memberName;
    private final int totalScore;
}
