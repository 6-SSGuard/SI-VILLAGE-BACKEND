package org.example.sivillage.vendor.infrastructure;

import org.example.sivillage.global.common.response.dto.ProductDto;
import org.springframework.data.domain.Slice;

public interface ProductCategoryListRepositoryCustom {
    Slice<ProductDto> findProductsByCategoriesWithCursorPaging(String topCategoryCode, String middleCategoryCode, String bottomCategoryCode,
                                                               String subCategoryCode, String lastProductCode,
                                                               int pageSize, String sort);
}