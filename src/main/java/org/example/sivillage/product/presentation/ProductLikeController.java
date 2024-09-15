package org.example.sivillage.product.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.product.application.ProductLikeService;
import org.example.sivillage.product.vo.out.GetLikeCountResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/product/like")
@RestController
public class ProductLikeController {

    private final ProductLikeService productLikeService;

    @Operation(summary = "상품 좋아요 수 조회")
    @GetMapping("/{productCode}")
    public BaseResponse<GetLikeCountResponseVo> getLikeCount(@PathVariable String productCode) {

        return new BaseResponse<>(
                productLikeService.getLikeCount(productCode).toVo()
        );
    }
}
