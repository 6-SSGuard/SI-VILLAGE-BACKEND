package org.example.sivillage.vendor.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryListRequestVo {
    private String topCategoryCode;
    private String middleCategoryCode;
    private String bottomCategoryCode;
    private String subCategoryCode;
    private String productCode;
}