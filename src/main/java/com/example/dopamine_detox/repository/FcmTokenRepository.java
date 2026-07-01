package com.example.dopamine_detox.repository;

import com.example.dopamine_detox.domain.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FcmTokenRepository extends JpaRepository<FcmToken,Long> {
}
