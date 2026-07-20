package com.example.dopamine_detox.dto.response;

import com.example.dopamine_detox.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String role;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.role = member.getRole().name();
    }
}
