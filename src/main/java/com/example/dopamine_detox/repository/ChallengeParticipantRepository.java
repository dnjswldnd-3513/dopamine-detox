package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.ChallengeParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeParticipantRepository extends JpaRepository<ChallengeParticipant,Long> {
}
