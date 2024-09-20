package org.example.sivillage.brand.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.brand.application.BrandLikeService;
import org.example.sivillage.brand.vo.out.GetBrandLikeResponseVo;
import org.example.sivillage.global.common.response.BaseResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BrandLikeController {

    private final BrandLikeService brandLikeService;

    /**
     * 4. getBrandLike 브랜드 좋아요 여부 조회
     * @param brandId 브랜드 ID
     * @param authUserDetails 인증된 사용자 정보
     * return GetBrandLikeResponseVo
     */
    @Operation(summary = "브랜드 좋아요 여부 조회", description = "브랜드 좋아요 여부를 조회합니다.", tags = "찜하기")
    @GetMapping("/{brandId}/like")
    public BaseResponse<GetBrandLikeResponseVo> getBrandLike(@PathVariable Long brandId, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return new BaseResponse<>(
                brandLikeService.getBrandLike(brandId, authUserDetails.getMemberUuid()).toVo()
        );
    }

    /**
     * 5. toggleBrandLike 브랜드 좋아요 토글
     * @param brandId 브랜드 ID
     * @param authUserDetails 인증된 사용자 정보
     * return void
     */
    @Operation(summary = "브랜드 좋아요 토글", description = "브랜드 좋아요를 토글합니다.", tags = "찜하기")
    @PutMapping("/{brandId}/like")
    public BaseResponse<Void> toggleBrandLike(@PathVariable Long brandId, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        brandLikeService.toggleBrandLike(brandId, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }
}
