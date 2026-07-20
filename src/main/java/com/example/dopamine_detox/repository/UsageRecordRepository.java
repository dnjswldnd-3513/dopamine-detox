package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.UsageRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsageRecordRepository extends JpaRepository<UsageRecord, Long> {
    List<UsageRecord> findByMemberId(Long memberId);
    List<UsageRecord> findByMemberIdAndAppCategoryId(Long memberId, Long appCategoryId);
}
