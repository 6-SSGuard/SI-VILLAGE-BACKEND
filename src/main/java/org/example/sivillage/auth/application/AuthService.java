package org.example.sivillage.auth.application;

import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.auth.dto.in.SignInRequestDto;
import org.example.sivillage.auth.dto.in.SignUpRequestDto;
import org.example.sivillage.auth.dto.out.JwtTokenResponseDto;
import org.example.sivillage.auth.vo.in.RefreshTokenRequestDto;

public interface AuthService {

    /**
     * AuthUserDetails service interface
     * 1. signUp
     * 2. signIn
     * 3. refreshAccessToken
     * 3. signOut
     */

    /**
     * 1. Sign up
     * Save user
     * @param signUpRequestDto
     * return void
     */
    void signUp(SignUpRequestDto signUpRequestDto);

    /**
     * 2. Sign in
     * Authenticate user
     * @param signInRequestDto
     * return signInResponseDto
     */
    JwtTokenResponseDto signIn(SignInRequestDto signInRequestDto);

    /**
     * 3. RefreshAccessToken
     * @param refreshTokenRequestDto
     * return JwtTokenResponseDto
     */
    JwtTokenResponseDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto);

    /**
     * 4. Sign out
     * @param authUserDetails
     * return void
     */
    void signOut(AuthUserDetails authUserDetails);
}
