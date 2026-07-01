package com.example.dopamine_detox.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
// DB 테이블인거 알려주는 어노테이션
@Getter
// lombok에서 가져온 기능 getName등 getter자동 생성

@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 파마리머타 없는 기본 생성자 생성, protected로 막아서 외부에서 new member()막기 아마 public~~ 아니라 protected asdf(){}인듯
public class Member {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 으로 숫자 올라가게 pk증가
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    //nullable = false --> DB에서 not null
    //unique --> 중복 이메일 방지

    @Enumerated(EnumType.STRING) //Enum 형태를 저장할떄 문자열로 저장
    private Role role;

    @Builder // Member.build().name().email() 형태로 저장 될수 있게
    public Member(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
