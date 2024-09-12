package org.example.sivillage.global.common;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.infrastructure.CategoryRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.vendor.domain.ProductCategoryList;
import org.example.sivillage.vendor.infrastructure.ProductCategoryListRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryPathService {

    private final ProductCategoryListRepository productCategoryListRepository;
    private final CategoryRepository categoryRepository;

    public String getCategoryPath(String productCode) {

        ProductCategoryList productCategoryList = productCategoryListRepository.findByProductCode(productCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        return String.join(" > ",
                categoryRepository.findCategoryNameByCategoryCode(productCategoryList.getTopCategoryCode()).orElse(""),
                categoryRepository.findCategoryNameByCategoryCode(productCategoryList.getMiddleCategoryCode()).orElse(""),
                categoryRepository.findCategoryNameByCategoryCode(productCategoryList.getBottomCategoryCode()).orElse(""),
                categoryRepository.findCategoryNameByCategoryCode(productCategoryList.getSubCategoryCode()).orElse("")
        ).trim();
    }
}
