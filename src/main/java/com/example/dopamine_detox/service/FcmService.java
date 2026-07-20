package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.FcmToken;
import com.example.dopamine_detox.domain.Member;
import com.example.dopamine_detox.repository.FcmTokenRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FcmService {

    private final FcmTokenRepository fcmTokenRepository;

    public void sendPush(Member member, String title, String body) {
        List<FcmToken> tokens = fcmTokenRepository.findByMemberId(member.getId());
        for (FcmToken fcmToken : tokens) {
            try {
                Message message = Message.builder()
                        .setToken(fcmToken.getToken())
                        .setNotification(Notification.builder()
                                .setTitle(title)
                                .setBody(body)
                                .build())
                        .build();
                FirebaseMessaging.getInstance().send(message);
            } catch (Exception e) {
                log.error("FCM send failed for token: {}", fcmToken.getToken(), e);
            }
        }
    }
}
