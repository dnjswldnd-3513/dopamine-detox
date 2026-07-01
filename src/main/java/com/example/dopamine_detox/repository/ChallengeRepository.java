package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.Challenge;
import com.example.dopamine_detox.domain.ChallengeParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge,Long> {
}
