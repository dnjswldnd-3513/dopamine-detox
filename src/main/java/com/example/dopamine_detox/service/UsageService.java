package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.AppCategory;
import com.example.dopamine_detox.domain.Goal;
import com.example.dopamine_detox.domain.Member;
import com.example.dopamine_detox.domain.ScoreReason;
import com.example.dopamine_detox.domain.UsageRecord;
import com.example.dopamine_detox.dto.request.UsageStartRequest;
import com.example.dopamine_detox.dto.request.UsageStopRequest;
import com.example.dopamine_detox.dto.response.UsageResponse;
import com.example.dopamine_detox.exception.EntityNotFoundException;
import com.example.dopamine_detox.repository.AppCategoryRepository;
import com.example.dopamine_detox.repository.GoalRepository;
import com.example.dopamine_detox.repository.MemberRepository;
import com.example.dopamine_detox.repository.UsageRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsageService {

    private final UsageRecordRepository usageRecordRepository;
    private final MemberRepository memberRepository;
    private final AppCategoryRepository appCategoryRepository;
    private final GoalRepository goalRepository;
    private final AlertService alertService;
    private final ScoreService scoreService;
    private final FcmService fcmService;

    @Transactional
    public UsageResponse start(Long memberId, UsageStartRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        AppCategory appCategory = appCategoryRepository.findById(request.getAppCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("AppCategory not found"));
        UsageRecord record = UsageRecord.builder()
                .member(member)
                .appCategory(appCategory)
                .startTime(LocalDateTime.now())
                .build();
        return new UsageResponse(usageRecordRepository.save(record));
    }

    @Transactional
    public UsageResponse stop(Long memberId, UsageStopRequest request) {
        UsageRecord record = usageRecordRepository.findById(request.getUsageRecordId())
                .orElseThrow(() -> new EntityNotFoundException("UsageRecord not found"));
        if (!record.getMember().getId().equals(memberId)) {
            throw new EntityNotFoundException("UsageRecord not found");
        }
        LocalDateTime endTime = LocalDateTime.now();
        int durationMinutes = (int) java.time.Duration.between(record.getStartTime(), endTime).toMinutes();
        record.finish(endTime, durationMinutes);
        checkGoalAndNotify(record);
        return new UsageResponse(record);
    }

    private void checkGoalAndNotify(UsageRecord record) {
        Optional<Goal> goalOpt = goalRepository.findByMemberIdAndAppCategoryId(
                record.getMember().getId(), record.getAppCategory().getId());
        if (goalOpt.isEmpty()) return;

        Goal goal = goalOpt.get();
        int todayTotal = usageRecordRepository
                .findByMemberIdAndAppCategoryId(record.getMember().getId(), record.getAppCategory().getId())
                .stream()
                .filter(r -> r.getStartTime().toLocalDate().equals(LocalDate.now()))
                .mapToInt(UsageRecord::getDurationMinutes)
                .sum();

        if (todayTotal > goal.getLimitMinutes()) {
            String message = record.getAppCategory().getName()
                    + " 사용 시간이 목표를 초과했습니다! (" + todayTotal + "/" + goal.getLimitMinutes() + "분)";
            alertService.createAlert(record.getMember(), record, message);
            fcmService.sendPush(record.getMember(), "목표 초과 알림", message);
            scoreService.addScore(record.getMember(), -5, ScoreReason.GOAL_EXCEEDED);
        } else {
            scoreService.addScore(record.getMember(), 10, ScoreReason.USAGE_REDUCED);
        }
    }

    public List<UsageResponse> getMyUsage(Long memberId) {
        return usageRecordRepository.findByMemberId(memberId).stream()
                .map(UsageResponse::new)
                .toList();
    }
}
