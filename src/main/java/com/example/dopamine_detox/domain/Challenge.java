package com.example.dopamine_detox.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appCategory_id")
    private AppCategory appCategory;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int limitMinutes;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private int bonusScore;

    @Builder
    public Challenge(AppCategory appCategory, String title, String description, int limitMinutes, LocalDate createdAt, LocalDate endDate, int bonusScore) {
        this.appCategory = appCategory;
        this.title = title;
        this.description = description;
        this.limitMinutes = limitMinutes;
        this.startDate = createdAt;
        this.endDate = endDate;
        this.bonusScore = bonusScore;
    }
}
