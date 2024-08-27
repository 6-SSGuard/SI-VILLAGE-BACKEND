package org.example.sivillage.product.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.member.application.ProductLikeService;
import org.example.sivillage.product.application.ProductService;
import org.example.sivillage.product.vo.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@Tag(name = "물품 관리 API", description = "물품 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final ProductLikeService productLikeService;
    private final ModelMapper mapper;

    @Operation(summary = "물품 생성", description = """
    
    """)
    @PostMapping("/")
    public BaseResponse<Void> addProduct(@Valid @RequestBody CreateProductRequestVo request) {
        productService.addProduct(request);
        return new BaseResponse<>();
    }

    @Operation(summary = "물품 상세 정보 조회", description = """
    
    """)
    @GetMapping("/details/{productCode}")
    public BaseResponse<GetProductDetailsResponseVo> getProductDetail(@PathVariable String productCode) {
        GetProductDetailsResponse response = productService.getProductDetail(productCode);
        return new BaseResponse<>(mapper.map(response, GetProductDetailsResponseVo.class));
    }

    @Operation(summary = "좋아요 추가", description = "특정 상품에 대해 좋아요를 추가합니다.")
    @PostMapping("/like")
    public BaseResponse<Void> likeProduct(@RequestBody LikeProductRequest request) {
        productLikeService.likeProduct(request);
        return new BaseResponse<>();
    }

    @Operation(summary = "좋아요 취소", description = "특정 상품에 대해 좋아요를 취소합니다.")
    @DeleteMapping("/like")
    public BaseResponse<Void> unlikeProduct(@RequestBody UnLikeProductRequest request) {
        productLikeService.unlikeProduct(request);
        return new BaseResponse<>();
    }
}
