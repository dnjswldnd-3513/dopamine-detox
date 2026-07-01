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
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usageRecord_id")
    private UsageRecord usageRecord;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // 생성자 만들떄 id를 추가 안하는건 JPa가 자동으로 generationType으로 id를 만들어주기 때문이다. build에 추가하면 우리가 직접 지정하는게 되서 충돌된다.
    @Builder
    public Alert(Member member, UsageRecord usageRecord, String message, LocalDateTime createdAt) {
        this.member = member;
        this.usageRecord = usageRecord;
        this.message = message;
        this.createdAt = createdAt;
    }
}

