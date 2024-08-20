package org.example.sivillage.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.auth.JwtToken;
import org.example.sivillage.global.auth.JwtTokenProvider;
import org.example.sivillage.global.error.CustomException;
import org.example.sivillage.global.error.ErrorCode;
import org.example.sivillage.domain.member.domain.Member;
import org.example.sivillage.domain.member.vo.SignUpRequest;
import org.example.sivillage.domain.member.infrastructure.MemberRepository;
import org.example.sivillage.global.util.SecurityUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUp(SignUpRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.STUDENT_ID_DUPLICATION);
        }
        // 넘겨받은 비밀번호를 인코딩하여 DB에 저장한다.
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // 정적 팩토리 메서드를 사용하여 Member 객체 생성
        Member member = Member.createMember(request, encodedPassword);
        memberRepository.save(member);
    }

    public JwtToken logIn(String code, String password) {
        // 1. code + password 기반으로 Authentication 객체 생성
        // 로그인 요청시에는 아직 미인증 상태이므로 authentication은 인증 여부를 확인하는 authenticated 값이 false 상태이다.

        try {
            // 1. code + password 기반으로 Authentication 객체 생성
            // 2. AuthenticationManager를 통한 인증 요청
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(code, password)
            );
            // 인증된 Authentication 객체 봔환
            // 인증 상태이므로 authentication은 인증 여부를 확인하는 authenticated 값이 true 상태이다.
            return jwtTokenProvider.generateToken(authentication);
        } catch (AuthenticationException e) {
            throw new CustomException(ErrorCode.LOGIN_FAILURE);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }

    public Member getSignedMember() {
        String email = SecurityUtil.getCurrentMemberEmail();
        return memberRepository.findByEmail(email).
                orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

}
