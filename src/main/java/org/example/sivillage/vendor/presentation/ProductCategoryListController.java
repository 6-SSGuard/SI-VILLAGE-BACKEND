package org.example.sivillage.vendor.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.common.response.vo.IdListResponseVo;
import org.example.sivillage.vendor.application.ProductCategoryListServiceImpl;
import org.example.sivillage.vendor.dto.in.ProductCategoryListRequestDto;
import org.example.sivillage.vendor.vo.ProductCategoryListRequestVo;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendor")
@Tag(name = "벤더의 상품 관리 API")
public class ProductCategoryListController {

    private final ProductCategoryListServiceImpl productCategoryListServiceImpl;
    private final ModelMapper mapper;

    /**
     * 1. 상품 카테고리 리스트 추가
     * 2. 특정 카테고리의 상품 id 리스트 조회
     */

    @Operation(summary = "상품 카테고리 리스트 추가", description = """
    subCategoryCode는 필수가 아니므로, 공백으로 넘겨줘도 됨.
    """)
    @PostMapping("/product-category-list")
    public BaseResponse<Void> addProductByCategories(@RequestBody ProductCategoryListRequestVo productCategoryListRequestVo) {

        ProductCategoryListRequestDto request = mapper.map(productCategoryListRequestVo, ProductCategoryListRequestDto.class);
        productCategoryListServiceImpl.addProductByCategories(request);

        return new BaseResponse<>();
    }

    @Operation(summary = "특정 카테고리의 상품 id 리스트 조회", description = "")
    @GetMapping("/product-category-list")
    public BaseResponse<List<IdListResponseVo<String>>> getProductCodeListByCategories(
            @RequestParam(value = "topCategoryCode", required = true) String topCategoryCode,
            @RequestParam(value = "middleCategoryCode", required = true) String middleCategoryCode,
            @RequestParam(value = "bottomCategoryCode", required = true) String bottomCategoryCode,
            @RequestParam(value = "subCategoryCode", required = false) String subCategoryCode) {

        List<IdListResponseDto<String>> idListResponseDto = productCategoryListServiceImpl.getProductCodeListByCategories(
                topCategoryCode, middleCategoryCode, bottomCategoryCode, subCategoryCode);

        return new BaseResponse<>(
                idListResponseDto.stream()
                        .map(IdListResponseDto::toVo)
                        .toList()
        );
    }
}
