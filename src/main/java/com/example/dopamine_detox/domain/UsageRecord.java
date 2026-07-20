package com.example.dopamine_detox.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_category_id")
    private AppCategory appCategory;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;       // stop 시 설정

    private int durationMinutes;         // stop 시 계산

    @Builder
    public UsageRecord(Member member, AppCategory appCategory, LocalDateTime startTime) {
        this.member = member;
        this.appCategory = appCategory;
        this.startTime = startTime;
    }

    public void finish(LocalDateTime endTime, int durationMinutes) {
        this.endTime = endTime;
        this.durationMinutes = durationMinutes;
    }
}
