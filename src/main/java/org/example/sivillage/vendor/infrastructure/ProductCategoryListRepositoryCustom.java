package org.example.sivillage.vendor.infrastructure;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ProductCategoryListRepositoryCustom {
    Slice<String> findProductsByCategoriesWithCursorPaging(String topCategoryCode, String middleCategoryCode, String bottomCategoryCode,
                                                           String subCategoryCode, String lastProductCode,
                                                           Pageable pageable);
}