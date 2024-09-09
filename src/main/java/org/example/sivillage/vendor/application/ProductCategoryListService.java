package org.example.sivillage.vendor.application;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.vendor.domain.ProductCategoryList;
import org.example.sivillage.vendor.dto.in.ProductCategoryListRequestDto;
import org.example.sivillage.vendor.dto.out.GetProductCategoryListResponseDto;
import org.example.sivillage.vendor.dto.out.GetProductCategoryResponseDto;
import org.example.sivillage.vendor.infrastructure.ProductCategoryListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        List<ProductCategoryList> productCategoryLists = productCategoryListRepository
                .findByCategories(topCategoryCode, middleCategoryCode, bottomCategoryCode, subCategoryCode);

        List<GetProductCategoryResponseDto> productCodeDtoList = productCategoryLists.stream()
                .map(productCategoryList -> GetProductCategoryResponseDto.toDto(productCategoryList.getProductCode()))
                .collect(Collectors.toList());

        return new GetProductCategoryListResponseDto(productCodeDtoList);
    }

}
