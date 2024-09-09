package org.example.sivillage.vendor.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.vendor.dto.in.ProductCategoryListRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductCategoryListResponseDto;
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

    public GetProductCategoryListResponseDto getProductCategoryListByCategories(
            String topCategoryCode,
            String middleCategoryCode,
            String bottomCategoryCode,
            String subCategoryCode) {

        List<String> productCodeList = productCategoryListRepository
                .findAllProductCodeByCategories(topCategoryCode, middleCategoryCode, bottomCategoryCode, subCategoryCode);

        return new GetProductCategoryListResponseDto(productCodeList);
    }

}
