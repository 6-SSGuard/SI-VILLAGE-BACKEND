package org.example.sivillage.vendor.application;

import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.vendor.dto.in.ProductCategoryListRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ProductCategoryListService {

    /**
     * 1. 상품 카테고리별 상품 추가
     *
     * @param productCategoryListRequestDto 상품 카테고리별 상품 추가 요청 DTO
     */
    void addProductByCategories(ProductCategoryListRequestDto productCategoryListRequestDto);

    /**
     * 2. 상품 카테고리별 상품 조회
     *
     * @param topCategoryCode    대분류 코드
     * @param middleCategoryCode 중분류 코드
     * @param bottomCategoryCode 소분류 코드
     * @param subCategoryCode    세부분류 코드
     * @return 상품 코드 목록
     */
    Slice<IdListResponseDto<String>> getProductCodeListByCategories(
            String topCategoryCode,
            String middleCategoryCode,
            String bottomCategoryCode,
            String subCategoryCode,
            String lastProductCode,
            Pageable pageable
            );
}
