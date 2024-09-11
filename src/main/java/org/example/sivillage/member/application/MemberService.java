package org.example.sivillage.member.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.auth.vo.SignUpRequest;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.auth.dto.out.JwtTokenResponseDto;
import org.example.sivillage.global.common.jwt.JwtTokenProvider;
import org.example.sivillage.global.util.SecurityUtil;
import org.example.sivillage.member.domain.Member;
import org.example.sivillage.member.infrastructure.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUp(SignUpRequest request) {
        if (Boolean.TRUE.equals(memberRepository.existsByEmail(request.getEmail()))) {
            throw new BaseException(BaseResponseStatus.STUDENT_ID_DUPLICATION);
        }

        String memberUuid = UUID.randomUUID().toString();

        // 넘겨받은 비밀번호를 인코딩하여 DB에 저장한다.
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // 정적 팩토리 메서드를 사용하여 Member 객체 생성
        Member member = Member.createMember(request, memberUuid, encodedPassword);
        memberRepository.save(member);
    }

    public JwtTokenResponseDto logIn(String email, String password) {
        // 1. email + password 기반으로 Authentication 객체 생성
        // 로그인 요청시에는 아직 미인증 상태이므로 authentication은 인증 여부를 확인하는 authenticated 값이 false 상태이다.

        try {
            // 1. email + password 기반으로 Authentication 객체 생성
            // 2. AuthenticationManager를 통한 인증 요청
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            // 인증된 Authentication 객체 봔환
            // 인증 상태이므로 authentication은 인증 여부를 확인하는 authenticated 값이 true 상태이다.
            return jwtTokenProvider.generateToken(authentication);
        } catch (AuthenticationException e) {
            throw new BaseException(BaseResponseStatus.LOGIN_FAILURE);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public Member getSignedMember() {
        String email = SecurityUtil.getCurrentMemberEmail();
        return memberRepository.findByEmail(email).
                orElseThrow(() -> new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND));
    }

    public String getMemberEmail(String memberUuid){
        return memberRepository.findByMemberUuid(memberUuid).get().getEmail();
    }
}
