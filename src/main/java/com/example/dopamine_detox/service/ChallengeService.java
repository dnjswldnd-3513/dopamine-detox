package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.AppCategory;
import com.example.dopamine_detox.domain.Challenge;
import com.example.dopamine_detox.domain.ChallengeParticipant;
import com.example.dopamine_detox.domain.Member;
import com.example.dopamine_detox.domain.ScoreReason;
import com.example.dopamine_detox.dto.request.ChallengeRequest;
import com.example.dopamine_detox.dto.request.JoinChallengeRequest;
import com.example.dopamine_detox.dto.response.ChallengeResponse;
import com.example.dopamine_detox.exception.DuplicateException;
import com.example.dopamine_detox.exception.EntityNotFoundException;
import com.example.dopamine_detox.repository.AppCategoryRepository;
import com.example.dopamine_detox.repository.ChallengeParticipantRepository;
import com.example.dopamine_detox.repository.ChallengeRepository;
import com.example.dopamine_detox.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeParticipantRepository challengeParticipantRepository;
    private final MemberRepository memberRepository;
    private final AppCategoryRepository appCategoryRepository;
    private final ScoreService scoreService;

    @Transactional
    public ChallengeResponse create(ChallengeRequest request) {
        AppCategory appCategory = appCategoryRepository.findById(request.getAppCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("AppCategory not found"));
        Challenge challenge = Challenge.builder()
                .appCategory(appCategory)
                .title(request.getTitle())
                .description(request.getDescription())
                .limitMinutes(request.getLimitMinutes())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .bonusScore(request.getBonusScore())
                .build();
        return new ChallengeResponse(challengeRepository.save(challenge));
    }

    public List<ChallengeResponse> getAll() {
        return challengeRepository.findAll().stream()
                .map(ChallengeResponse::new)
                .toList();
    }

    @Transactional
    public void join(Long memberId, JoinChallengeRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Challenge challenge = challengeRepository.findById(request.getChallengeId())
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
        if (challengeParticipantRepository.existsByMemberIdAndChallengeId(memberId, challenge.getId())) {
            throw new DuplicateException("Already joined this challenge");
        }
        ChallengeParticipant participant = ChallengeParticipant.builder()
                .member(member)
                .challenge(challenge)
                .build();
        challengeParticipantRepository.save(participant);
    }

    @Transactional
    public void complete(Long memberId, Long challengeId) {
        ChallengeParticipant participant = challengeParticipantRepository
                .findByMemberIdAndChallengeId(memberId, challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Participant not found"));
        participant.complete();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        scoreService.addScore(member, participant.getChallenge().getBonusScore(), ScoreReason.CHALLENGE_DONE);
    }
}
