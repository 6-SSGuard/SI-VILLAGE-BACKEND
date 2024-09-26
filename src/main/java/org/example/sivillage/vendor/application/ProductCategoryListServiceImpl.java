package org.example.sivillage.vendor.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.dto.CustomSlice;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.common.response.dto.ProductDto;
import org.example.sivillage.vendor.dto.in.ProductCategoryListRequestDto;
import org.example.sivillage.vendor.infrastructure.ProductCategoryListRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    private final ProductCategoryListRepository productCategoryListRepository;

    public void addProductByCategories(ProductCategoryListRequestDto productCategoryListRequestDto) {
        productCategoryListRepository.save(productCategoryListRequestDto.toEntity());
    }

    @Override
    public CustomSlice<IdListResponseDto<String>> getProductCodeListByCategories(
            String topCategoryCode,
            String middleCategoryCode,
            String bottomCategoryCode,
            String subCategoryCode,
            String lastProductCode,
            int pageSize, String sort) {


        Slice<ProductDto> productCodesSlice = productCategoryListRepository
                .findProductsByCategoriesWithCursorPaging(topCategoryCode, middleCategoryCode,
                        bottomCategoryCode, subCategoryCode, lastProductCode, pageSize, sort);

        // 마지막 값 처리 (가격 또는 날짜)
        String lastValue = null;
        if (productCodesSlice.hasContent()) {
            ProductDto lastItem = productCodesSlice.getContent().get(productCodesSlice.getContent().size() - 1);
            Object value = lastItem.getValue();  // 가격 또는 생성일자를 가져옴
            lastValue = value != null ? value.toString().replace(" ", "T") : null;  // LocalDateTime이면 변환
        }

        // CustomSlice 객체로 변환하여 lastValue와 함께 반환
        return new CustomSlice<>(
                productCodesSlice.map(productDto -> new IdListResponseDto<>(productDto.getProductCode())).getContent(),
                Pageable.unpaged(),
                productCodesSlice.hasNext(),
                lastValue
        );

    }
}
