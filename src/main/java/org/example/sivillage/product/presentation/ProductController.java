package org.example.sivillage.product.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.member.application.ProductLikeService;
import org.example.sivillage.product.application.ProductService;
import org.example.sivillage.product.vo.in.CreateProductRequestVo;
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

    @Operation(summary = "특정 물품의 2차 카테고리 이름 반환", description = "test용")
    @GetMapping("/{productUuid}/top-category-name")
    public BaseResponse<String> getTopLevelCategoryName(@PathVariable String productUuid) {
        String categoryName = productService.getSecondLevelCategoryName(productUuid);
        return new BaseResponse<>(categoryName);
    }

}
