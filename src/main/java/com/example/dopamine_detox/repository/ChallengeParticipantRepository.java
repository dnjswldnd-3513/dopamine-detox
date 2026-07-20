package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.ChallengeParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallengeParticipantRepository extends JpaRepository<ChallengeParticipant, Long> {
    boolean existsByMemberIdAndChallengeId(Long memberId, Long challengeId);
    Optional<ChallengeParticipant> findByMemberIdAndChallengeId(Long memberId, Long challengeId);
}
