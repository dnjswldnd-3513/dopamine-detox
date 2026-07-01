package com.example.dopamine_detox.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 필요할때만 db 조회한다는것
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appCategory_id")
    private AppCategory appCategory;

    @Column(nullable = false)
    private int limitMinutes;

    @Builder
    public Goal( Member member, AppCategory appCategory,int limitMinutes ) {
        this.member = member;
        this.appCategory = appCategory;
        this.limitMinutes = limitMinutes;
    }
}
