package org.example.sivillage.brand.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.brand.application.BrandService;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.vo.in.AddBrandRequestVo;
import org.example.sivillage.brand.vo.out.GetBrandNameResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.common.response.vo.IdListResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "브랜드 관리 API", description = "브랜드 관련 API endpoints")
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;

    /**
     * 1. addBrand 브랜드 추가
     * 2. getBrandIdList 브랜드 목록 조회
     * 3. getBrandName 브랜드 이름 조회
     * 4. getBrandLike 브랜드 좋아요 여부 조회
     * 5. toggleBrandLike 브랜드 좋아요 토글
     */


    /**
     * 1. addBrand 브랜드 추가
     * @param addBrandRequestVo 브랜드 추가 요청 정보
     * return void
     */
    @Operation(summary = "브랜드 추가")
    @PostMapping("/")
    public BaseResponse<Void> addBrand(@Valid @RequestBody AddBrandRequestVo addBrandRequestVo) {

        brandService.addBrand(AddBrandRequestDto.from(addBrandRequestVo));

        return new BaseResponse<>();
    }

    /**
     * 2. getBrandIdList 브랜드 id 리스트 조회
     * @param authUserDetails 인증된 사용자 정보
     * return GetBrandIdListResponseVo
     */
    @Operation(summary = "브랜드 id 리스트 조회")
    @GetMapping("/")
    public BaseResponse<List<IdListResponseVo<Long>>> getBrandIdList(@AuthenticationPrincipal AuthUserDetails authUserDetails) {

        List<IdListResponseDto<Long>> idListResponseDto = brandService.getBrandIdList(authUserDetails.getMemberUuid());

        return new BaseResponse<>(
                idListResponseDto.stream()
                        .map(IdListResponseDto::toVo)
                        .toList()
        );
    }

    /**
     * 3. getBrandName 브랜드 이름 조회
     * @param brandId 브랜드 ID
     * return GetBrandNameResponseVo
     */
    @Operation(summary = "브랜드 정보 조회")
    @GetMapping("/{brandId}")
    public BaseResponse<GetBrandNameResponseVo> getBrandName(@PathVariable Long brandId) {
        return new BaseResponse<>(
                brandService.getBrandName(brandId).toVo()
        );
    }

    /**
     * 4. addBrandFromCsv CSV 파일로 브랜드 추가
     * @param file CSV 파일
     * return void
     */
    @Operation(summary = "CSV 파일로 브랜드 추가")
    @PostMapping(value = "/csv", consumes = "multipart/form-data")
    public BaseResponse<Void> addBrandFromCsv(@RequestParam("file") MultipartFile file) {
        brandService.addBrandFromCsv(file);
        return new BaseResponse<>();
    }
}
