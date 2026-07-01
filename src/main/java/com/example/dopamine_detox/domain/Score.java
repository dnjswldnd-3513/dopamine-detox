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
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private int point;

    @Enumerated(EnumType.STRING)
    private ScoreReason reason;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder

    public Score(Member member, int point, ScoreReason reason, LocalDateTime createdAt) {
        this.member = member;
        this.point = point;
        this.reason = reason;
        this.createdAt = createdAt;
    }
}
