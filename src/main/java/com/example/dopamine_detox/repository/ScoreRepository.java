package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score,Long> {
}
