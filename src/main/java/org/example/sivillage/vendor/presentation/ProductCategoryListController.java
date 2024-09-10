package org.example.sivillage.vendor.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.vendor.application.ProductCategoryListService;
import org.example.sivillage.vendor.dto.in.ProductCategoryListRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductCategoryListResponseDto;
import org.example.sivillage.vendor.vo.GetProductCategoryListResponseVo;
import org.example.sivillage.vendor.vo.ProductCategoryListRequestVo;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendor")
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;
    private final ModelMapper mapper;

    @Operation(summary = "상품 카테고리 리스트 추가", description = """
    subCategoryCode는 필수가 아니므로, 공백으로 넘겨줘도 됨.
    """)
    @PostMapping("/product-category-list")
    public BaseResponse<Void> addProductByCategories(@RequestBody ProductCategoryListRequestVo productCategoryListRequestVo) {

        ProductCategoryListRequestDto request = mapper.map(productCategoryListRequestVo, ProductCategoryListRequestDto.class);
        productCategoryListService.addProductByCategories(request);

        return new BaseResponse<>();
    }

    @Operation(summary = "상품 카테고리 리스트 조회", description = "상품 카테고리 리스트를 조회합니다.")
    @GetMapping("/product-category-list")
    public BaseResponse<GetProductCategoryListResponseVo> getProductCategoryListByCategories(
            @RequestParam(value = "topCategoryCode", required = true) String topCategoryCode,
            @RequestParam(value = "middleCategoryCode", required = true) String middleCategoryCode,
            @RequestParam(value = "bottomCategoryCode", required = true) String bottomCategoryCode,
            @RequestParam(value = "subCategoryCode", required = false) String subCategoryCode) {

        GetProductCategoryListResponseDto responseDto = productCategoryListService.getProductCategoryListByCategories(
                topCategoryCode, middleCategoryCode, bottomCategoryCode, subCategoryCode);

        return new BaseResponse<>(mapper.map(responseDto, GetProductCategoryListResponseVo.class));
    }
}
