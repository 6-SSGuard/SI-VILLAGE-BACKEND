package org.example.sivillage.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.CustomUserDetails;
import org.example.sivillage.global.common.CustomResponseEntity;
import org.example.sivillage.member.application.BeautyInfoService;
import org.example.sivillage.member.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.member.dto.out.BeautyInfoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 관리 API", description = "회원 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final BeautyInfoService beautyInfoService;

    @Operation(summary = "뷰티 정보 등록", description = "뷰티정보를 등록합니다.")
    @PostMapping("/beauty-info")
    public CustomResponseEntity<Void> addBeautyInfo(@Valid @RequestBody BeautyInfoRequestDto dto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = "uuid";
        beautyInfoService.addBeautyInfo(dto, memberUuid);
        return new CustomResponseEntity<>(HttpStatus.OK, "뷰티정보 등록을 완료하였습니다.");
    }

    @Operation(summary = "뷰티 정보 조회", description = "뷰티정보를 조회합니다.")
    @GetMapping("/beauty-info")
    public CustomResponseEntity<BeautyInfoResponseDto> getBeautyInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = "uuid";
        BeautyInfoResponseDto responseDto = beautyInfoService.getBeautyInfo(memberUuid);
        return new CustomResponseEntity<>(HttpStatus.OK, responseDto, "뷰티정보 조회를 완료하였습니다.");
    }

    @Operation(summary = "뷰티 정보 수정", description = "뷰티정보를 수정합니다.")
    @PutMapping("/beauty-info")
    public CustomResponseEntity<Void> changeBeautyInfo(@Valid @RequestBody BeautyInfoRequestDto dto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = "uuid";
        beautyInfoService.changeBeautyInfo(dto, memberUuid);
        return new CustomResponseEntity<>(HttpStatus.OK, "뷰티정보 수정을 완료하였습니다.");
    }
}






