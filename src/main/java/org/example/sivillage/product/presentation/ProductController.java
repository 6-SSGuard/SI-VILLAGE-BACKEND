package org.example.sivillage.product.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.member.application.ProductLikeService;
import org.example.sivillage.product.application.ProductService;
import org.example.sivillage.product.vo.CreateProductRequest;
import org.example.sivillage.product.vo.LikeProductRequest;
import org.example.sivillage.product.vo.UnLikeProductRequest;
import org.example.sivillage.global.common.CustomResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "물품 관리 API", description = "물품 관련 API endpoints")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final ProductLikeService productLikeService;

    @Operation(summary = "물품 생성", description = """
    
    """)
    @PostMapping("/")
    public CustomResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest request) {
        productService.createProduct(request);
        return new CustomResponseEntity<>(HttpStatus.OK, "물품 생성이 완료되었습니다.");
    }

    @Operation(summary = "물품 상세 정보 조회", description = """
    
    """)
    @GetMapping("/details/{productCode}")
    public CustomResponseEntity<?> getProductDetails(@PathVariable String productCode) {
        return new CustomResponseEntity<>(HttpStatus.OK, productService.getProductDetails(productCode),
                "물품 상세정보 조회가 완료되었습니다.");
    }

    @Operation(summary = "좋아요 추가", description = "특정 상품에 대해 좋아요를 추가합니다.")
    @PostMapping("/like")
    public CustomResponseEntity<?> likeProduct(@RequestBody LikeProductRequest request) {
        productLikeService.likeProduct(request);
        return new CustomResponseEntity<>(HttpStatus.OK, "좋아요가 추가되었습니다.");
    }

    @Operation(summary = "좋아요 취소", description = "특정 상품에 대해 좋아요를 취소합니다.")
    @DeleteMapping("/like")
    public CustomResponseEntity<?> unlikeProduct(@RequestBody UnLikeProductRequest request) {
        productLikeService.unlikeProduct(request);
        return new CustomResponseEntity<>(HttpStatus.OK, "좋아요가 취소되었습니다.");
    }
}
