package org.example.sivillage.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.common.Response;
import org.example.sivillage.global.auth.CustomUserDetails;
import org.example.sivillage.global.auth.JwtToken;
import org.example.sivillage.global.auth.JwtTokenProvider;
import org.example.sivillage.member.application.MemberService;
import org.example.sivillage.member.dto.LogInRequest;
import org.example.sivillage.member.dto.RefreshTokenRequest;
import org.example.sivillage.member.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "회원 관리 API", description = "회원 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "회원가입", description = """
    code: String, 6자리 이상
    
    password: 비밀번호는 최소 8자 이상, 영문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.
    
    role: STUDENT, PROFESSOR, ADMIN 중에서 택 1
    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "409", description = "이메일 중복")
    })
    @PostMapping("/signup")
    public ResponseEntity<Response<Void>> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        memberService.signUp(signUpRequest);
        return ResponseEntity.ok(new Response<>(null, "회원가입이 완료되었습니다!"));
    }

    @Operation(summary = "로그인", description = """
    로그인 기능입니다.
    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "사용자 없음")
    })
    @PostMapping("/login")
    public ResponseEntity<Response<JwtToken>> logIn(@Valid @RequestBody LogInRequest request) {
        String code = request.getEmail();
        String password = request.getPassword();


        JwtToken jwtToken = memberService.logIn(code, password);
        log.debug("request code = {}, password = {}", code, password);
        log.debug("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());

        return ResponseEntity.ok(new Response<>(jwtToken, "로그인 성공."));

    }


    @Operation(summary = "Access Token 재발급", description = "Access Token 만료시 기존에 발급받은 Refresh Token을 이쪽으로 보내서 새로운 Access Token 받아가기")
    @PostMapping("/refresh")
    public ResponseEntity<Response<JwtToken>> refreshAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        JwtToken token = jwtTokenProvider.refreshAccessToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok(new Response<>(token, "Access Token 재발급 완료."));
    }

    @Operation(summary = "로그아웃", description = "현재 로그인 된 계정의 로그아웃")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/logout")
    public ResponseEntity<Response<Void>> logout(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String studentId = customUserDetails.getUsername();
        jwtTokenProvider.deleteRefreshToken(studentId);
        return ResponseEntity.ok(new Response<>(null, "로그아웃 되었습니다."));
    }

    @Operation(summary = "swagger test", description = "swagger 테스트를 위한 엔드포인트 입니다.")
    @GetMapping("/test")
    public ResponseEntity<Response<List<Integer>>> test() {
        List<Integer> testList = List.of(1, 2, 3, 4, 5); //테스트
        return ResponseEntity.ok(new Response<>(testList, "테스트 리스트 반환 완료"));
    }
}