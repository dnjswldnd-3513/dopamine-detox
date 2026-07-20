package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.Member;
import com.example.dopamine_detox.dto.response.RankResponse;
import com.example.dopamine_detox.repository.MemberRepository;
import com.example.dopamine_detox.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankService {

    private final MemberRepository memberRepository;
    private final ScoreRepository scoreRepository;

    public List<RankResponse> getRanking() {
        List<Member> members = memberRepository.findAll();
        AtomicInteger rank = new AtomicInteger(1);
        return members.stream()
                .map(member -> {
                    int total = scoreRepository.findByMemberId(member.getId()).stream()
                            .mapToInt(s -> s.getPoint())
                            .sum();
                    return new RankResponse(0, member.getName(), total);
                })
                .sorted(Comparator.comparingInt(RankResponse::getTotalScore).reversed())
                .map(r -> new RankResponse(rank.getAndIncrement(), r.getMemberName(), r.getTotalScore()))
                .toList();
    }
}
