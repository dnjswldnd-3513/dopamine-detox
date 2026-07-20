package com.example.dopamine_detox.service;

import com.example.dopamine_detox.domain.Member;
import com.example.dopamine_detox.domain.Role;
import com.example.dopamine_detox.dto.request.LoginRequest;
import com.example.dopamine_detox.dto.request.SignupRequest;
import com.example.dopamine_detox.dto.response.LoginResponse;
import com.example.dopamine_detox.dto.response.MemberResponse;
import com.example.dopamine_detox.exception.BadRequestException;
import com.example.dopamine_detox.exception.DuplicateException;
import com.example.dopamine_detox.exception.EntityNotFoundException;
import com.example.dopamine_detox.repository.MemberRepository;
import com.example.dopamine_detox.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResponse signup(SignupRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateException("Email already in use: " + request.getEmail());
        }
        Member member = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        return new MemberResponse(memberRepository.save(member));
    }

    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new BadRequestException("Invalid password");
        }
        String token = jwtUtil.generateToken(member.getId());
        return new LoginResponse(token, member.getName(), member.getRole().name());
    }

    public MemberResponse getMyInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return new MemberResponse(member);
    }
}
