package org.example.sivillage.vendor.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.common.response.vo.IdListResponseVo;
import org.example.sivillage.vendor.application.ProductCategoryListService;
import org.example.sivillage.vendor.dto.in.ProductCategoryListRequestDto;
import org.example.sivillage.vendor.vo.ProductCategoryListRequestVo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
@Tag(name = "벤더의 상품 관리 API")
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;
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
        productCategoryListService.addProductByCategories(request);

        return new BaseResponse<>();
    }

    @Operation(
            summary = "특정 카테고리의 상품 ID 리스트 조회",
            description = """
            주어진 상위, 중간, 하위 및 서브 카테고리 코드에 해당하는 상품 목록을 조회합니다.
            상품 목록은 페이징 처리되며, 사용자는 `sortBy` 파라미터를 통해 정렬 기준을 선택할 수 있습니다.
            페이징 처리된 상품의 마지막 ID를 기준으로 커서 페이징이 적용됩니다.

            만약 `lastProductCode`가 주어지지 않으면, 첫 페이지를 조회합니다.

            sortBy 파라미터는 다음과 같은 옵션을 제공합니다:
            - `priceAsc`: 할인율이 적용된 가격을 기준으로 오름차순 정렬
            - `priceDesc`: 할인율이 적용된 가격을 기준으로 내림차순 정렬
            - `discountAsc`: 할인율을 기준으로 오름차순 정렬
            - `discountDesc`: 할인율을 기준으로 내림차순 정렬
            - `newest`: 신상품순 (등록일 기준 내림차순)으로 정렬
            """
            , tags = {"상품 정보 조회"}
    )
    @GetMapping("/product-category-list")
    public BaseResponse<Slice<IdListResponseVo<String>>> getProductCodeListByCategories(
            @RequestParam(value = "topCategoryCode", required = true) String topCategoryCode,
            @RequestParam(value = "middleCategoryCode", required = true) String middleCategoryCode,
            @RequestParam(value = "bottomCategoryCode", required = true) String bottomCategoryCode,
            @RequestParam(value = "subCategoryCode", required = false) String subCategoryCode,
            @RequestParam(value = "lastProductCode", required = false) String lastProductCode,
            Pageable pageable) {

        Slice<IdListResponseDto<String>> productCodes = productCategoryListService.getProductCodeListByCategories(
                topCategoryCode, middleCategoryCode, bottomCategoryCode, subCategoryCode, lastProductCode,
                pageable);

        return new BaseResponse<>(productCodes.map(IdListResponseDto::toVo));
    }
}
