package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.FcmToken;
import com.example.dopamine_detox.domain.Member;
import com.example.dopamine_detox.dto.request.FcmTokenRequest;
import com.example.dopamine_detox.exception.EntityNotFoundException;
import com.example.dopamine_detox.repository.FcmTokenRepository;
import com.example.dopamine_detox.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FcmTokenService {

    private final FcmTokenRepository fcmTokenRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveToken(Long memberId, FcmTokenRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        if (!fcmTokenRepository.existsByToken(request.getToken())) {
            FcmToken fcmToken = FcmToken.builder()
                    .member(member)
                    .token(request.getToken())
                    .build();
            fcmTokenRepository.save(fcmToken);
        }
    }

    @Transactional
    public void deleteToken(Long memberId, String token) {
        FcmToken fcmToken = fcmTokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Token not found"));
        if (!fcmToken.getMember().getId().equals(memberId)) {
            throw new EntityNotFoundException("Token not found");
        }
        fcmTokenRepository.delete(fcmToken);
    }
}
