package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal,Long> {
}
