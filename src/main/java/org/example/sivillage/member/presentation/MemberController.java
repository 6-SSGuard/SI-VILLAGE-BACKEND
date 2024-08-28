package org.example.sivillage.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.CustomUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.member.application.BeautyInfoService;
import org.example.sivillage.member.application.SizeInfoService;
import org.example.sivillage.member.dto.in.BeautyInfoRequestDto;
import org.example.sivillage.member.dto.in.SizeInfoRequestDto;
import org.example.sivillage.member.dto.out.BeautyInfoResponseDto;
import org.example.sivillage.member.dto.out.SizeInfoResponseDto;
import org.example.sivillage.member.vo.in.BeautyInfoRequestVo;
import org.example.sivillage.member.vo.out.BeautyInfoResponseVo;
import org.example.sivillage.member.vo.in.SizeInfoRequestVo;
import org.example.sivillage.member.vo.out.SizeInfoResponseVo;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 관리 API", description = "회원 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final BeautyInfoService beautyInfoService;
    private final SizeInfoService sizeInfoService;
    private final ModelMapper modelMapper;

    @Operation(summary = "뷰티 정보 등록", description = "뷰티정보를 등록합니다.")
    @PostMapping("/beauty-info")
    public BaseResponse<Void> addBeautyInfo(@Valid @RequestBody BeautyInfoRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        beautyInfoService.addBeautyInfo(modelMapper.map(vo, BeautyInfoRequestDto.class), memberUuid); // vo -> dto
        return new BaseResponse<>();
    }

    @Operation(summary = "뷰티 정보 조회", description = "뷰티정보를 조회합니다.")
    @GetMapping("/beauty-info")
    public BaseResponse<BeautyInfoResponseVo> getBeautyInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        BeautyInfoResponseDto dto = beautyInfoService.getBeautyInfo(memberUuid);
        return new BaseResponse<>(modelMapper.map(dto, BeautyInfoResponseVo.class));
    }

    @Operation(summary = "뷰티 정보 수정", description = "뷰티정보를 수정합니다.")
    @PutMapping("/beauty-info")
    public BaseResponse<Void> changeBeautyInfo(@Valid @RequestBody BeautyInfoRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        beautyInfoService.changeBeautyInfo(modelMapper.map(vo, BeautyInfoRequestDto.class), memberUuid);
        return new BaseResponse<>();
    }

    @Operation(summary = "뷰티 정보 삭제", description = "뷰티 정보를 삭제합니다.")
    @DeleteMapping("/beauty-info")
    public BaseResponse<Void> deleteBeautyInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        beautyInfoService.removeBeautyInfo(memberUuid);
        return new BaseResponse<>();
    }

    @Operation(summary = "사이즈 정보 등록", description = "사이즈 정보를 등록합니다.")
    @PostMapping("/size-info")
    public BaseResponse<Void> addSizeInfo(@Valid @RequestBody SizeInfoRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        log.info("SizeInfoRequestVo: {}", vo);
        sizeInfoService.addSizeInfo(modelMapper.map(vo, SizeInfoRequestDto.class), memberUuid); // vo -> dto
        return new BaseResponse<>();
    }

    @Operation(summary = "사이즈 정보 조회", description = "사이즈 정보를 조회합니다.")
    @GetMapping("/size-info")
    public BaseResponse<SizeInfoResponseVo> getSizeInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        SizeInfoResponseDto dto = sizeInfoService.getSizeInfo(memberUuid);
        return new BaseResponse<>(modelMapper.map(dto, SizeInfoResponseVo.class));
    }

    @Operation(summary = "사이즈 정보 수정", description = "사이즈 정보를 수정합니다.")
    @PutMapping("/size-info")
    public BaseResponse<Void> changeSizeInfo(@Valid @RequestBody SizeInfoRequestVo vo, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        sizeInfoService.changeSizeInfo(modelMapper.map(vo, SizeInfoRequestDto.class), memberUuid);
        return new BaseResponse<>();

    }


    @Operation(summary = "사이즈 정보 삭제", description = "사이즈 정보를 삭제합니다.")
    @DeleteMapping("/size-info")
    public BaseResponse<Void> deleteSizeInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String memberUuid = customUserDetails.getMemberUuid();
        sizeInfoService.removeSizeInfo(memberUuid);
        return new BaseResponse<>();
    }

}















