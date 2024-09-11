package org.example.sivillage.auth.application;

import org.example.sivillage.auth.dto.in.SignInRequestDto;
import org.example.sivillage.auth.dto.in.SignUpRequestDto;
import org.example.sivillage.auth.dto.out.JwtTokenResponseDto;

public interface AuthService {

    /**
     * AuthUserDetails service interface
     * 1. signUp
     * 2. signIn
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

}
