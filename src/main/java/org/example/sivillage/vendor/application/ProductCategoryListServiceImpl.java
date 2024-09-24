package org.example.sivillage.vendor.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
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
    public Slice<IdListResponseDto<String>> getProductCodeListByCategories(
            String topCategoryCode,
            String middleCategoryCode,
            String bottomCategoryCode,
            String subCategoryCode,
            String lastProductCode,
            Pageable pageable) {


        return productCategoryListRepository
                .findProductsByCategoriesWithCursorPaging(topCategoryCode, middleCategoryCode,
                        bottomCategoryCode, subCategoryCode, lastProductCode, pageable)
                .map(IdListResponseDto::from);
    }
}
