package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.UsageRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageRecordRepository extends JpaRepository<UsageRecord,Long> {
}
