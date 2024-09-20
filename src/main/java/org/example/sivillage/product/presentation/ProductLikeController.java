package org.example.sivillage.product.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.auth.domain.AuthUserDetails;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.product.application.ProductLikeService;
import org.example.sivillage.product.vo.out.GetLikeCountResponseVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/product/like")
@RestController
@Tag(name= "상품 관리 API", description = "상품 관련 API endpoints")
public class ProductLikeController {

    private final ProductLikeService productLikeService;


    @Operation(summary = "상품 좋아요 버튼 토글", description = "좋아요 -> 좋아요 해제, 좋아요 해제 -> 좋아요")
    @PutMapping("/product/like/{productUuid}")
    public BaseResponse<Void> toggleProductLike(@PathVariable String productUuid, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        productLikeService.toggleProductLike(productUuid, authUserDetails.getMemberUuid());
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 좋아요 수 조회")
    @GetMapping("/{productCode}")
    public BaseResponse<GetLikeCountResponseVo> getLikeCount(@PathVariable String productCode) {

        return new BaseResponse<>(
                productLikeService.getLikeCount(productCode).toVo()
        );
    }
}
