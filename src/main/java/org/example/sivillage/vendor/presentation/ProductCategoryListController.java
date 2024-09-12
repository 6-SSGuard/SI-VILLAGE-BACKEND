package org.example.sivillage.vendor.presentation;

import io.swagger.v3.oas.annotations.Operation;
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
public class ProductCategoryListController {

    private final ProductCategoryListServiceImpl productCategoryListServiceImpl;
    private final ModelMapper mapper;

    /**
     * 1. 상품 카테고리별 상품 추가
     * 2. 상품 카테고리별 상품 조회
     */


    /**
     * 1. 상품 카테고리별 상품 추가
     * @param productCategoryListRequestVo 상품 카테고리별 상품 추가 요청 VO
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

    /**
     * 2. 상품 카테고리별 상품 조회
     * @param topCategoryCode    대분류 코드
     * @param middleCategoryCode 중분류 코드
     * @param bottomCategoryCode 소분류 코드
     * @param subCategoryCode    세부 분류 코드
     * @return 상품 코드 목록
     */
    @Operation(summary = "상품 카테고리 리스트 조회", description = "상품 카테고리 리스트를 조회합니다.")
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
