package org.example.sivillage.brand.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.brand.application.BrandService;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandIdListResponseDto;
import org.example.sivillage.brand.vo.in.AddBrandRequestVo;
import org.example.sivillage.brand.vo.out.GetBrandIdListResponseVo;
import org.example.sivillage.brand.vo.out.GetBrandInfoResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "브랜드 관리 API", description = "브랜드 관련 API endpoints")
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;
    private final ModelMapper mapper;

    @Operation(summary = "브랜드 추가")
    @PostMapping("/")
    public BaseResponse<Void> addBrand(@Valid @RequestBody AddBrandRequestVo addBrandRequestVo) {

        brandService.addBrand(AddBrandRequestDto.from(addBrandRequestVo));

        return new BaseResponse<>();
    }

    @Operation(summary = "브랜드 목록 조회")
    @GetMapping("/")
    public BaseResponse<List<GetBrandIdListResponseVo>> getBrandIdList(@AuthenticationPrincipal AuthUserDetails authUserDetails) {

        return new BaseResponse<>(
                brandService.getBrandIdList(authUserDetails.getMemberUuid()).stream()
                        .map(GetBrandIdListResponseDto::toVo)
                        .toList()
        );
    }

    @Operation(summary = "브랜드 정보 조회")
    @GetMapping("/{brandId}")
    public BaseResponse<GetBrandInfoResponseVo> getBrandInfo(@PathVariable Long brandId) {
        return new BaseResponse<>(
                brandService.getBrandInfo(brandId).toVo()
        );
    }
}
