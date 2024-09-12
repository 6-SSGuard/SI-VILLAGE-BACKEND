package org.example.sivillage.global.common;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.domain.Category;
import org.example.sivillage.admin.infrastructure.CategoryRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.error.BaseException;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.product.infrastructure.ProductRepository;
import org.example.sivillage.vendor.domain.ProductCategoryList;
import org.example.sivillage.vendor.infrastructure.ProductCategoryListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryPathService {

    private final ProductCategoryListRepository productCategoryListRepository;
    private final ProductRepository productRepository;


    // 카테고리 경로 가져오기
    public String getCategoryPath(String productUuid) {

        Product product = productRepository.findByProductCode(productUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PRODUCT_NOT_FOUND));

        ProductCategoryList productCategoryList = productCategoryListRepository.findByCategoryCode(product.getCategoryCode())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND));

        List<String> categoryPath = new ArrayList<>();

        while (category != null) {
            categoryPath.add(category.getCategoryName());
            category = category.getParent(); // 부모 카테고리로 이동
        }

        // 상위 카테고리부터 하위 카테고리 순으로 정렬
        Collections.reverse(categoryPath);

        return String.join("/", categoryPath);
    }

}
