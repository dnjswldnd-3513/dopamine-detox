package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByMemberId(Long memberId);
    Optional<Goal> findByMemberIdAndAppCategoryId(Long memberId, Long appCategoryId);
}
