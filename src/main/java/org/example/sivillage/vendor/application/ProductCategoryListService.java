package org.example.sivillage.vendor.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.vendor.dto.in.ProductCategoryListRequestDto;
import org.example.sivillage.vendor.dto.out.ProductCategoryListResponseDto;
import org.example.sivillage.vendor.infrastructure.ProductCategoryListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductCategoryListService {

    private final ProductCategoryListRepository productCategoryListRepository;

    public void addProductByCategories(ProductCategoryListRequestDto productCategoryListRequestDto) {
        productCategoryListRepository.save(productCategoryListRequestDto.toEntity());
    }

    public List<ProductCategoryListResponseDto> getProductCategoryListByCategories(
            String topCategoryCode,
            String middleCategoryCode,
            String bottomCategoryCode,
            String subCategoryCode) {

        return productCategoryListRepository
                .findByCategories(topCategoryCode, middleCategoryCode, bottomCategoryCode, subCategoryCode)
                .stream()
                .map(productCategoryList -> ProductCategoryListResponseDto.builder()
                       .productCode(productCategoryList.getProductCode())
                       .build())
                .toList();
    }
}
