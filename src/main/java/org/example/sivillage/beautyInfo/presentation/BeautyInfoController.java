package org.example.sivillage.beautyInfo.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.beautyInfo.application.BeautyInfoService;
import org.example.sivillage.beautyInfo.dto.out.BeautyInfoResponseDto;
import org.example.sivillage.beautyInfo.vo.in.BeautyInfoRequestVo;
import org.example.sivillage.beautyInfo.vo.out.BeautyInfoResponseVo;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 뷰티정보 관리 API", description = "뷰티정보 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/beauty-info")
public class BeautyInfoController {

    private final BeautyInfoService beautyInfoService;

    @Operation(summary = "뷰티 정보 등록", description = "뷰티정보를 등록합니다.")
    @PostMapping()
    public BaseResponse<Void> addBeautyInfo(@Valid @RequestBody BeautyInfoRequestVo beautyInfoRequestVo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        beautyInfoService.addBeautyInfo(BeautyInfoRequestVo.toDto(beautyInfoRequestVo), authUserDetails.getMemberUuid()); // vo -> dto
        return new BaseResponse<>();
    }

    @Operation(summary = "뷰티 정보 조회", description = "뷰티정보를 조회합니다.")
    @GetMapping()
    public BaseResponse<BeautyInfoResponseVo> getBeautyInfo(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        BeautyInfoResponseDto beautyInfoResponseDto  = beautyInfoService.getBeautyInfo(authUserDetails.getMemberUuid());
        return new BaseResponse<>(beautyInfoResponseDto.toResponseVo());
    }

    @Operation(summary = "뷰티 정보 수정", description = "뷰티정보를 수정합니다.")
    @PutMapping()
    public BaseResponse<Void> changeBeautyInfo(@Valid @RequestBody BeautyInfoRequestVo beautyInfoRequestVo, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        beautyInfoService.changeBeautyInfo(BeautyInfoRequestVo.toDto(beautyInfoRequestVo), authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "뷰티 정보 삭제", description = "뷰티 정보를 삭제합니다.")
    @DeleteMapping()
    public BaseResponse<Void> deleteBeautyInfo(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        beautyInfoService.removeBeautyInfo(authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

}
