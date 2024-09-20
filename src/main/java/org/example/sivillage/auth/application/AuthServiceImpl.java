package org.example.sivillage.auth.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.auth.dto.in.SignInRequestDto;
import org.example.sivillage.auth.dto.in.SignUpRequestDto;
import org.example.sivillage.auth.vo.in.RefreshTokenRequestDto;
import org.example.sivillage.global.common.jwt.JwtTokenProvider;
import org.example.sivillage.auth.dto.out.JwtTokenResponseDto;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.member.domain.Member;
import org.example.sivillage.member.infrastructure.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * AuthServiceImpl
     * 1. 회원가입
     * 2. 로그인
     * 3. 로그아웃
     */

    /**
     * 1. 회원가입
     * Save user
     * @param signUpRequestDto
     * return void
     */
    @Override
    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {

        log.info("signUpRequestDto : {}", signUpRequestDto);

        try {
            memberRepository.save(signUpRequestDto.toEntity(passwordEncoder));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_REGISTRATION);
        }

    }

    /**
     * 2. 로그인
     * Authenticate user
     * @param signInRequestDto
     * return signInResponseDto
     */
    @Override
    @Transactional
    public JwtTokenResponseDto signIn(SignInRequestDto signInRequestDto) {

        log.info("jwtTokenRequestDto : {}", signInRequestDto);
        Member member = memberRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.FAILED_TO_LOGIN)
        );

        try
        {
            JwtTokenResponseDto jwtTokenResponseDto = createToken(authenticate(member, signInRequestDto.getPassword()));
            jwtTokenResponseDto.setName(member.getName());
            log.info("token : {}", jwtTokenResponseDto);

            return jwtTokenResponseDto;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        }

    }

    private JwtTokenResponseDto createToken(Authentication authentication) {
        return jwtTokenProvider.generateToken(authentication);
    }

    private Authentication authenticate(Member member, String inputPassword) {
        AuthUserDetails authUserDetail = new AuthUserDetails(member);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authUserDetail.getUsername(),
                        inputPassword,
                        authUserDetail.getAuthorities()
                )
        );
    }

    /**
     * 3. RefreshAccessToken
     * @param refreshTokenRequestDto
     * return JwtTokenResponseDto
     */
    @Override
    public JwtTokenResponseDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        return jwtTokenProvider.refreshAccessToken(refreshTokenRequestDto.getRefreshToken());
    }

    /**
     * 4. Sign out
     * @param authUserDetails
     * return void
     */
    @Override
    public void signOut(AuthUserDetails authUserDetails) {
        jwtTokenProvider.deleteRefreshToken(authUserDetails.getUsername());
    }
}
