package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
    List<FcmToken> findByMemberId(Long memberId);
    Optional<FcmToken> findByToken(String token);
    boolean existsByToken(String token);
}
