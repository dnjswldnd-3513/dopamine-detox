package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.Alert;
import com.example.dopamine_detox.domain.Member;
import com.example.dopamine_detox.domain.UsageRecord;
import com.example.dopamine_detox.dto.response.AlertResponse;
import com.example.dopamine_detox.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlertService {

    private final AlertRepository alertRepository;

    @Transactional
    public void createAlert(Member member, UsageRecord usageRecord, String message) {
        Alert alert = Alert.builder()
                .member(member)
                .usageRecord(usageRecord)
                .message(message)
                .createdAt(LocalDateTime.now())
                .build();
        alertRepository.save(alert);
    }

    public List<AlertResponse> getMyAlerts(Long memberId) {
        return alertRepository.findByMemberId(memberId).stream()
                .map(AlertResponse::new)
                .toList();
    }
}
