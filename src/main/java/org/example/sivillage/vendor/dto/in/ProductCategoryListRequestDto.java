package org.example.sivillage.vendor.dto.in;

import lombok.*;
import org.example.sivillage.vendor.domain.ProductCategoryList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryListRequestDto {

    private String topCategoryCode;
    private String middleCategoryCode;
    private String bottomCategoryCode;
    private String subCategoryCode;
    private String productCode;

    public ProductCategoryList toEntity() {
        return ProductCategoryList.builder()
                .topCategoryCode(topCategoryCode)
                .middleCategoryCode(middleCategoryCode)
                .bottomCategoryCode(bottomCategoryCode)
                .subCategoryCode(subCategoryCode)
                .productCode(productCode)
                .build();
    }
}
