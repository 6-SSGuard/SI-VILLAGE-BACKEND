package org.example.sivillage.vendor.presentation;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.BaseResponse;
import org.example.sivillage.vendor.application.ProductCategoryListService;
import org.example.sivillage.vendor.dto.in.ProductCategoryListRequestDto;
import org.example.sivillage.vendor.vo.ProductCategoryListRequestVo;
import org.example.sivillage.vendor.vo.ProductCategoryListResponseVo;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendor")
public class ProductCategoryListCategory {

    private final ProductCategoryListService productCategoryListService;
    private final ModelMapper mapper;

    @PostMapping("/product-category-list")
    public BaseResponse<Void> addProductByCategories(@RequestBody ProductCategoryListRequestVo productCategoryListRequestVo) {

        ProductCategoryListRequestDto request = mapper.map(productCategoryListRequestVo, ProductCategoryListRequestDto.class);
        productCategoryListService.addProductByCategories(request);

        return new BaseResponse<>();
    }

    @GetMapping("/product-category-list")
    //TODO: productCode가 null로 반환되는 문제 해결하기
    public BaseResponse<List<ProductCategoryListResponseVo>> getProductCategoryListByCategories(
            @RequestParam(value = "topCategoryCode", required = true) String topCategoryCode,
            @RequestParam(value = "middleCategoryCode", required = true) String middleCategoryCode,
            @RequestParam(value = "bottomCategoryCode", required = true) String bottomCategoryCode,
            @RequestParam(value = "subCategoryCode", required = false) String subCategoryCode) {

        List<ProductCategoryListResponseVo> response = productCategoryListService.getProductCategoryListByCategories(
                topCategoryCode, middleCategoryCode, bottomCategoryCode, subCategoryCode)
                .stream()
                .map(productCategoryList -> mapper.map(productCategoryList, ProductCategoryListResponseVo.class))
                .collect(Collectors.toList());

        return new BaseResponse<>(response);
    }
}
