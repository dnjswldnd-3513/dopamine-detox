package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.AppCategory;
import com.example.dopamine_detox.domain.Goal;
import com.example.dopamine_detox.domain.Member;
import com.example.dopamine_detox.dto.request.GoalRequest;
import com.example.dopamine_detox.dto.response.GoalResponse;
import com.example.dopamine_detox.exception.EntityNotFoundException;
import com.example.dopamine_detox.repository.AppCategoryRepository;
import com.example.dopamine_detox.repository.GoalRepository;
import com.example.dopamine_detox.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoalService {

    private final GoalRepository goalRepository;
    private final MemberRepository memberRepository;
    private final AppCategoryRepository appCategoryRepository;

    @Transactional
    public GoalResponse create(Long memberId, GoalRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        AppCategory appCategory = appCategoryRepository.findById(request.getAppCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("AppCategory not found"));
        Goal goal = Goal.builder()
                .member(member)
                .appCategory(appCategory)
                .limitMinutes(request.getLimitMinutes())
                .build();
        return new GoalResponse(goalRepository.save(goal));
    }

    public List<GoalResponse> getMyGoals(Long memberId) {
        return goalRepository.findByMemberId(memberId).stream()
                .map(GoalResponse::new)
                .toList();
    }

    @Transactional
    public void delete(Long goalId, Long memberId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found"));
        if (!goal.getMember().getId().equals(memberId)) {
            throw new EntityNotFoundException("Goal not found");
        }
        goalRepository.delete(goal);
    }
}
