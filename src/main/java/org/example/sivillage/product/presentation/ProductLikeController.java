package org.example.sivillage.product.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.product.application.ProductLikeService;
import org.example.sivillage.product.vo.out.GetLikeCountResponseVo;
import org.example.sivillage.product.vo.out.GetLikeInfoResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/product-like/member/")
@RestController
public class ProductLikeController {

    private final ProductLikeService productLikeService;


    @Operation(summary = "상품 좋아요 버튼 토글", description = "좋아요 -> 좋아요 해제, 좋아요 해제 -> 좋아요", tags = "찜하기")
    @PutMapping("/{productCode}")
    public BaseResponse<Void> toggleProductLike(@PathVariable String productCode, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        productLikeService.toggleProductLike(productCode, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 좋아요 수 조회", description = "상품의 좋아요 수 조회", tags = "상품 정보 조회")
    @GetMapping("/{productCode}")
    public BaseResponse<GetLikeCountResponseVo> getLikeCount(@PathVariable String productCode) {

        return new BaseResponse<>(
                productLikeService.getLikeCount(productCode).toVo()
        );
    }

    @Operation(summary = "상품 좋아요 여부 조회", description = "상품의 좋아요 여부 조회", tags = "상품 정보 조회")
    @GetMapping("/like/{productCode}")
    public BaseResponse<GetLikeInfoResponseVo> getLikeInfo(@PathVariable String productCode, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return new BaseResponse<>(
                productLikeService.getLikeInfo(productCode, authUserDetails.getMemberUuid()).toVo()
        );
    }
}
