package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.Goal;
import com.example.dopamine_detox.domain.UsageRecord;
import com.example.dopamine_detox.dto.response.StatsResponse;
import com.example.dopamine_detox.repository.GoalRepository;
import com.example.dopamine_detox.repository.UsageRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatsService {

    private final UsageRecordRepository usageRecordRepository;
    private final GoalRepository goalRepository;

    public List<StatsResponse> getTodayStats(Long memberId) {
        List<UsageRecord> todayRecords = usageRecordRepository.findByMemberId(memberId).stream()
                .filter(r -> r.getStartTime().toLocalDate().equals(LocalDate.now()))
                .toList();
        Map<String, Integer> usageByCategory = todayRecords.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getAppCategory().getName(),
                        Collectors.summingInt(UsageRecord::getDurationMinutes)
                ));
        List<Goal> goals = goalRepository.findByMemberId(memberId);
        return goals.stream()
                .map(goal -> {
                    String categoryName = goal.getAppCategory().getName();
                    int totalMinutes = usageByCategory.getOrDefault(categoryName, 0);
                    return new StatsResponse(categoryName, totalMinutes, goal.getLimitMinutes(), totalMinutes > goal.getLimitMinutes());
                })
                .toList();
    }
}
