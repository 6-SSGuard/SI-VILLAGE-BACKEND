package org.example.sivillage.vendor.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.vendor.dto.in.ProductCategoryListRequestDto;
import org.example.sivillage.vendor.infrastructure.ProductCategoryListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductCategoryListServiceImpl {

    private final ProductCategoryListRepository productCategoryListRepository;

    public void addProductByCategories(ProductCategoryListRequestDto productCategoryListRequestDto) {
        productCategoryListRepository.save(productCategoryListRequestDto.toEntity());
    }

    public List<IdListResponseDto<String>> getProductCodeListByCategories(
            String topCategoryCode,
            String middleCategoryCode,
            String bottomCategoryCode,
            String subCategoryCode) {

        return productCategoryListRepository
                .findAllProductCodeByCategories(topCategoryCode, middleCategoryCode, bottomCategoryCode, subCategoryCode)
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }
}
